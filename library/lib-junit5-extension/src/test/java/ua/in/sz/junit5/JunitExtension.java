package ua.in.sz.junit5;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.*;

@Slf4j
public class JunitExtension
        implements BeforeAllCallback, AfterAllCallback,
        BeforeEachCallback, AfterEachCallback,
        BeforeTestExecutionCallback, AfterTestExecutionCallback,
        TestInstancePostProcessor{
    @Override
    public void beforeAll(ExtensionContext context) {
        log.info("beforeAll");
    }

    @Override
    public void afterAll(ExtensionContext context) {
        log.info("afterAll");
    }

    @Override
    public void afterEach(ExtensionContext context) {
        log.info("afterEach");
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        log.info("beforeEach");
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        log.info("afterTestExecution");
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) {
        log.info("beforeTestExecution");
    }

    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext context) {
        log.info("postProcessTestInstance");
    }
}
