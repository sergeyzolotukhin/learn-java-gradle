package ua.in.szolotukhin.flowable.parallel.message;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

@Slf4j
public class InstantiateProcessByMessageDelegate implements JavaDelegate {

    public void execute(DelegateExecution execution) {
        log.info("message received processing");

        RuntimeService runtimeService = ParallelMessageApplication.getEngine().getRuntimeService();
        runtimeService.startProcessInstanceByMessage("instantiationMessage");

        log.info("message received processed");
    }
}