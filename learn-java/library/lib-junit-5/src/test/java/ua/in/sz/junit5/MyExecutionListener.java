package ua.in.sz.junit5;

import lombok.extern.slf4j.Slf4j;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestPlan;

@Slf4j
public class MyExecutionListener implements TestExecutionListener {
    @Override
    public void testPlanExecutionStarted(TestPlan testPlan) {
        log.error("Executing testPlan: {}", testPlan);
    }

    @Override
    public void testPlanExecutionFinished(TestPlan testPlan) {
        log.error("Finished testPlan: {}", testPlan);
    }
}
