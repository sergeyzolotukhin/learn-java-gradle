package ua.in.szolotukhin.flowable.common.tasks;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.flowable.bpmn.model.ServiceTask;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

@Slf4j
public class AsyncServiceTask implements JavaDelegate {
	@Override
	@SneakyThrows
	public void execute(DelegateExecution execution) {
		ServiceTask serviceTask = (ServiceTask)execution.getCurrentFlowElement();

		log.info("Executing task {}: async={}, exclusive={}", serviceTask.getId(),
				serviceTask.isAsynchronous(), serviceTask.isExclusive());

		Thread.sleep(1000 * 3);

		log.info("Executed task {}", serviceTask.getId());
	}
}
