package ua.in.szolotukhin.flowable.process;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static ua.in.szolotukhin.flowable.common.ProcessEngineFactory.*;

@Slf4j
public class ParentChildApplication {
	private static final String PARENT_PATH = "process-parent.bpmn";
	private static final String CHILD_PATH = "process-child.bpmn";

	private static final boolean HOME = true;

	public static void main(String[] args) {
		log.info("start application");

		ProcessEngine engine = createEngine(false);

		deployProcess(engine, CHILD_PATH);
		String processDefinitionKey = deployProcess(engine, PARENT_PATH);

        startProcess(engine, processDefinitionKey);

        log.info("End application");
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
