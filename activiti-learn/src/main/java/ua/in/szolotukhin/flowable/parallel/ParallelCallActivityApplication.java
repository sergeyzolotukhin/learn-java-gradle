package ua.in.szolotukhin.flowable.parallel;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.ProcessEngine;
import ua.in.szolotukhin.flowable.common.ProcessEngineFactory;
import ua.in.szolotukhin.flowable.common.ProcessUtils;

@Slf4j
public class ParallelCallActivityApplication {
	private static final String PARENT_PATH = "parallel/activity/parent.bpmn";
	private static final String CHILD_PATH = "parallel/activity/child.bpmn";

	public static void main(String[] args) {
		log.info("start application");

		ProcessEngine engine = ProcessEngineFactory.createEngine(false);

		ProcessUtils.deploy(engine, CHILD_PATH);
		String processDefinitionKey = ProcessUtils.deploy(engine, PARENT_PATH);
		ProcessUtils.start(engine, processDefinitionKey);

        log.info("End application");
	}
}
