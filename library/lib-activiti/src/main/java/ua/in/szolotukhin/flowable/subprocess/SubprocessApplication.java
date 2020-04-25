package ua.in.szolotukhin.flowable.subprocess;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.slf4j.MDC;
import ua.in.szolotukhin.flowable.common.ProcessEngineFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Slf4j
public class SubprocessApplication {
	private static final String PARENT_PATH = "subprocess-event.bpmn";

	public static void main(String[] args) {
		log.info("start application");

		ProcessEngine engine = ProcessEngineFactory.createEngine(false);

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
		MDC.put("pid", UUID.randomUUID().toString());

		try {
			log.info("Starting process {}", processDefinitionKey);

			Map<String, Object> variables = new HashMap<>();

			ProcessInstance process = engine.getRuntimeService()
					.startProcessInstanceById(processDefinitionKey, variables);

			log.info("Started process {}", processDefinitionKey);
		} finally {
			MDC.remove("process-id");
		}
	}
}
