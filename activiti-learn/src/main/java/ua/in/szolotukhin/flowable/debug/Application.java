package ua.in.szolotukhin.flowable.debug;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.model.*;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.impl.event.BreakpointJobHandler;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ActivityInstance;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.util.CollectionUtils;
import ua.in.szolotukhin.flowable.debug.engine.impl.cmd.CreateProcessInstanceCmd;
import ua.in.szolotukhin.flowable.debug.engine.impl.agenda.AdminFlowableEngineAgendaFactory;
import ua.in.szolotukhin.flowable.debug.engine.impl.bpmn.parser.factory.AdminActivityBehaviorFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class Application {
    private static final String USER_TASK_PATH = "usertask.bpmn";
    private static final String EVENT_WAIT_PATH = "eventwait.bpmn";

    private static final boolean HOME = true;
    private static final boolean ASYNC = false;

    public static void main(String[] args) {
        createSchema();

        ProcessEngine engine = createWorkEngine(false, false);

        String processDefinitionKey = deployProcess(engine, EVENT_WAIT_PATH);
        String processInstanceId = startProcess(engine, processDefinitionKey);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            List<String> activityIds = activityIds(engine, processInstanceId);
            if (CollectionUtils.isEmpty(activityIds)) {
                break;
            }

            System.out.println("Activities: '" + String.join(", ", activityIds + ". Please select activity: "));
            String activityId = selectIds(scanner, activityIds);

            List<String> targetActivityIds = activityIds(engine, processInstanceId, activityId);
            if (CollectionUtils.isEmpty(targetActivityIds)) {
                stepOver(engine, processInstanceId, null);
            } else {
                System.out.println("Flows: '" + String.join(", ", targetActivityIds) + ". Please select flow: ");
                String targetActivityId = selectIds(scanner, targetActivityIds);

                stepOver(engine, processInstanceId, targetActivityId);
            }
        }
    }

    private static void createSchema() {
        createWorkEngine(true, false);
    }

    private static String deployProcess(ProcessEngine engine, String processPath) {
        log.info("Deploy {}", processPath);

        Deployment deploy = engine.getRepositoryService()
                .createDeployment()
                .addClasspathResource(processPath)
                .deploy();

        Objects.requireNonNull(deploy, "Can't deploy file '" + processPath + "");

        ProcessDefinition definition = engine.getRepositoryService()
                .createProcessDefinitionQuery()
                .deploymentId(deploy.getId())
                .singleResult();

        return definition.getKey();
    }

    private static String startProcess(ProcessEngine engine, String processDefinitionKey) {
        log.info("Start process {}", processDefinitionKey);

        Map<String, Object> variables = new HashMap<>();
        variables.put("employee", "serhij.zolotukhin");
        variables.put("nrOfHolidays", 10);
        variables.put("description", "Annual vacation");

        ProcessInstance process = engine.getManagementService()
                .executeCommand(new CreateProcessInstanceCmd<ProcessInstance>(processDefinitionKey, null, null, null));

        Objects.requireNonNull(process, "Can't start process for process definition key '" + processDefinitionKey + "");

        return process.getId();
    }

    private static List<String> activityIds(ProcessEngine engine, String processInstanceId) {
        List<Execution> executions = engine.getRuntimeService()
                .createExecutionQuery()
                .processInstanceId(processInstanceId)
                .list();

        Objects.requireNonNull(executions, "Execution not found for processInstanceId: '" + processInstanceId + "'");

        return executions.stream()
                .map(Execution::getActivityId)
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.toList());
    }

    private static List<String> activityIds(ProcessEngine engine, String processInstanceId, String activityId) {
        ProcessInstance processInstance = engine.getRuntimeService()
                .createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();

        Objects.requireNonNull(processInstance,
                "Process instance not found for processInstance '" + processInstance + "'");

        String processDefinitionId = processInstance.getProcessDefinitionId();
        BpmnModel bpmnModel = engine.getRepositoryService().getBpmnModel(processDefinitionId);

        FlowNode flowElement = (FlowNode) bpmnModel.getFlowElement(activityId);

        Objects.requireNonNull(flowElement,
                "Flow element '" + activityId + "' not found for processDefinitionId: '" + processDefinitionId + "'");

        return outgoingFlowNodes(flowElement).stream()
                .map(BaseElement::getId)
                .distinct()
                .collect(Collectors.toList());
    }

    private static List<FlowNode> outgoingFlowNodes(FlowNode flowElement) {
        return flowElement.getOutgoingFlows().stream()
                .map(SequenceFlow::getTargetFlowElement)
                .filter(e -> e instanceof FlowNode)
                .map(e -> (FlowNode)e)
                .flatMap(n -> Stream.concat(Stream.of(n), outgoingFlowNodes(n).stream()))
                .collect(Collectors.toList());
    }

    private static String selectIds(Scanner scanner, List<String> ids) {
        String activityId = scanner.nextLine();
        if (StringUtils.isNotBlank(activityId)) {
            return activityId;
        }

        return ids.stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Option list is empty"));
    }

    private static void stepOver(ProcessEngine engine, String processInstanceId, String newActivityId) {
        log.info("Move to activity '{}' in process '{}'", newActivityId, processInstanceId);

        ActivityInstance activity = engine.getRuntimeService().createActivityInstanceQuery()
                .processInstanceId(processInstanceId)
                .unfinished()
                .singleResult();

        engine.getRuntimeService().createChangeActivityStateBuilder()
                .processInstanceId(processInstanceId)
                .processVariable("userTaskResult", "INBOUND")
                .moveActivityIdTo(activity.getActivityId(), newActivityId)
                .changeState();
    }

    // ================================================================================================================
    // Process engine factory method
    // ================================================================================================================

    private static ProcessEngine createWorkEngine(boolean recreateSchema, boolean debug) {
        StandaloneProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration();

        if (debug) {
            AdminFlowableEngineAgendaFactory agendaFactory = new AdminFlowableEngineAgendaFactory();
            agendaFactory.setDebugger(execution -> true);

            cfg.setAgendaFactory(agendaFactory)
                    .addCustomJobHandler(new BreakpointJobHandler());
            cfg.setActivityBehaviorFactory(new AdminActivityBehaviorFactory());
        }

        if (HOME) {
            cfg.setJdbcUrl("jdbc:postgresql://localhost/flowable")
                    .setJdbcUsername("flowable")
                    .setJdbcPassword("flowable")
                    .setJdbcDriver("org.postgresql.Driver");
        } else {
            cfg.setJdbcUrl("jdbc:oracle:thin:@localhost:1521/XE")
                    .setJdbcUsername("FLOWABLE_OWNER")
                    .setJdbcPassword("FLOWABLE_OWNER")
                    .setJdbcDriver("oracle.jdbc.driver.OracleDriver");
        }

        if (recreateSchema) {
            cfg.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_DROP_CREATE);
        } else {
            cfg.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        }

        if (ASYNC) {
            cfg.setAsyncExecutorActivate(true);
        }

        return cfg.buildProcessEngine();
    }
}
