package ua.in.szolotukhin.flowable.common;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
public final class ProcessUtils {
	public static String deploy(ProcessEngine engine, String processPath) {
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

	public static void start(ProcessEngine engine, String processDefinitionKey) {
		log.info("Starting process {}", processDefinitionKey);

		Map<String, Object> variables = new HashMap<>();

		ProcessInstance process = engine.getRuntimeService()
				.startProcessInstanceById(processDefinitionKey, variables);

		log.info("End process {}", processDefinitionKey);
	}
}
