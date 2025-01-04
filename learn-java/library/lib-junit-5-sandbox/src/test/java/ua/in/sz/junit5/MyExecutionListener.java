package ua.in.sz.junit5;

import lombok.extern.slf4j.Slf4j;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import org.junit.platform.launcher.TestPlan;

@Slf4j
public class MyExecutionListener implements TestExecutionListener {
    @Override
    public void testPlanExecutionStarted(TestPlan testPlan) {
        log.info("Executing testPlan: {}", testPlan);
    }

    @Override
    public void testPlanExecutionFinished(TestPlan testPlan) {
        log.info("Finished testPlan: {}", testPlan);
    }

    @Override
    public void executionStarted(TestIdentifier testIdentifier) {
        log.info("Executing testIdentifier: {}", testIdentifier);
    }

    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
        log.info("Finished testIdentifier: {}", testIdentifier);
    }
}
