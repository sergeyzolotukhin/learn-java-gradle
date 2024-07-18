package ua.in.sz.junit5;

import lombok.extern.slf4j.Slf4j;
import org.junit.platform.engine.EngineExecutionListener;
import org.junit.platform.engine.TestDescriptor;
import org.junit.platform.engine.TestExecutionResult;

@Slf4j
public class MyEngineExecutionListener implements EngineExecutionListener {

    @Override
    public void executionStarted(TestDescriptor testDescriptor) {
        log.info("executionStarted {}", testDescriptor);
    }

    @Override
    public void executionFinished(TestDescriptor testDescriptor, TestExecutionResult testExecutionResult) {
        log.info("executionFinished {}", testDescriptor);
    }


}
