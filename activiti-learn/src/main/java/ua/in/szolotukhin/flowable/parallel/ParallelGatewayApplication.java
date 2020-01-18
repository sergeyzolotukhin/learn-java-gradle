package ua.in.szolotukhin.flowable.parallel;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.ProcessEngine;
import ua.in.szolotukhin.flowable.common.ProcessEngineFactory;
import ua.in.szolotukhin.flowable.common.ProcessUtils;

@Slf4j
public class ParallelGatewayApplication {
	private static final String EVENT_WAIT_PATH = "parallel/gateway/gateway.bpmn";

	public static void main(String[] args) {
		log.info("start application");

		ProcessEngine engine = ProcessEngineFactory.createEngine(false);

		String processDefinitionKey = ProcessUtils.deploy(engine, EVENT_WAIT_PATH);
		ProcessUtils.start(engine, processDefinitionKey);

        log.info("End application");
	}
}
