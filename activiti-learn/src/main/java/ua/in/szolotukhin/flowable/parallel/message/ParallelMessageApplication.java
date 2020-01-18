package ua.in.szolotukhin.flowable.parallel.message;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.ProcessEngine;
import ua.in.szolotukhin.flowable.common.ProcessEngineFactory;
import ua.in.szolotukhin.flowable.common.ProcessUtils;

@Slf4j
public class ParallelMessageApplication {
	private static final String PARENT_PATH = "parallel/message/parent.bpmn";
	private static final String CHILD_PATH = "parallel/message/child.bpmn";

	private static ProcessEngine engine;

	public static ProcessEngine getEngine() {
		return engine;
	}

	public static void main(String[] args) {
		log.info("start application");

		engine = ProcessEngineFactory.createEngine(false);

		ProcessUtils.deploy(engine, CHILD_PATH);
		String processDefinitionKey = ProcessUtils.deploy(engine, PARENT_PATH);

		ProcessUtils.start(engine, processDefinitionKey);

        log.info("End application");
	}
}
