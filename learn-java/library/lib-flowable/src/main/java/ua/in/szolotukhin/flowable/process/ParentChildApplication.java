package ua.in.szolotukhin.flowable.process;

import lombok.extern.slf4j.Slf4j;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.EndEvent;
import org.flowable.bpmn.model.ExclusiveGateway;
import org.flowable.bpmn.model.SequenceFlow;
import org.flowable.bpmn.model.StartEvent;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;

import java.util.*;

import static ua.in.szolotukhin.flowable.common.ProcessEngineFactory.*;

@Slf4j
public class ParentChildApplication {
	private static final String PARENT_PATH = "process-parent.bpmn";
	private static final String CHILD_PATH = "process-child.bpmn";

	private static final boolean HOME = true;

	public static void main(String[] args) throws Exception {
		log.info("start application");

		ProcessEngine engine = createEngine(true);

		deployProcess(engine, CHILD_PATH);
		String processDefinitionKey = deployProcess(engine, PARENT_PATH);

        startProcess(engine, processDefinitionKey);

//		method(engine);

		log.info("End application");

		Thread.sleep(60_000 * 30);
		engine.close();

		log.info("The process engine closed");
	}

	private static void method(ProcessEngine processEngine) {
		BpmnModel bpmnModel = new BpmnModel();
		org.flowable.bpmn.model.Process process = new org.flowable.bpmn.model.Process();
		bpmnModel.addProcess(process);
		process.setId("holidayRequest");

		StartEvent startEvent = new StartEvent();
		startEvent.setId("startEvent");

		SequenceFlow sequenceFlow = new SequenceFlow("startEvent", "approveTask");

		ExclusiveGateway exclusiveGateway = new ExclusiveGateway();
		exclusiveGateway.setId("decision");

		SequenceFlow sequenceFlow2 = new SequenceFlow("decision", "externalSystemCall");
		SequenceFlow sequenceFlow3 = new SequenceFlow("decision", "sendRejectionMail");

		SequenceFlow sequenceFlow4 = new SequenceFlow("externalSystemCall", "holidayApprovedTask");
		SequenceFlow sequenceFlow5 = new SequenceFlow("holidayApprovedTask", "approveEnd");

		EndEvent endEvent = new EndEvent();
		endEvent.setId("approveEnd");

		process.addFlowElement(startEvent);
		process.addFlowElement(sequenceFlow);
		process.addFlowElement(exclusiveGateway);
		process.addFlowElement(sequenceFlow2);
		process.addFlowElement(sequenceFlow3);
		process.addFlowElement(sequenceFlow4);
		process.addFlowElement(sequenceFlow5);
		process.addFlowElement(endEvent);

		// Deploy

		RepositoryService repositoryService = processEngine.getRepositoryService();
		Deployment deployment = repositoryService.createDeployment()
				.addBpmnModel("holidayRequest", bpmnModel).deploy();

//		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
//				.deploymentId(deployment.getId())
//				.singleResult();

//		log.info("Found process definition : {}", processDefinition.getName());

		RuntimeService runtimeService = processEngine.getRuntimeService();
		ProcessInstance processInstance =
				runtimeService.startProcessInstanceByKey("holidayRequest");
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

		return definition.getId();
	}

	private static void startProcess(ProcessEngine engine, String processDefinitionKey) {
		log.info("Starting process {}", processDefinitionKey);

		Map<String, Object> variables = new HashMap<>();

		ProcessInstance process = engine.getRuntimeService()
				.startProcessInstanceById(processDefinitionKey, variables);

		log.info("Started process {}", processDefinitionKey);
	}
}
