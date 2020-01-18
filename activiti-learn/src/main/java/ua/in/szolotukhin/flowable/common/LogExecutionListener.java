package ua.in.szolotukhin.flowable.common;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;

@Slf4j
public class LogExecutionListener implements ExecutionListener {
	@Override
	public void notify(DelegateExecution execution) {
		log.info("Executed event: {}, activity: {}", execution.getEventName(), execution.getProcessDefinitionId());
	}
}
