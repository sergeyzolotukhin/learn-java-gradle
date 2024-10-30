package ua.in.sz.junit5.extension.test.instance;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;
import org.junit.jupiter.api.extension.TestInstancePreDestroyCallback;

@Slf4j
public class JunitTestInstanceExtension
        implements BeforeAllCallback, AfterAllCallback,
        TestInstancePreDestroyCallback, TestInstancePostProcessor{
    @Override
    public void beforeAll(ExtensionContext context) {
        log.info("beforeAll");
    }

    @Override
    public void afterAll(ExtensionContext context) {
        log.info("afterAll");
    }

    @Override
    public void preDestroyTestInstance(ExtensionContext context) throws Exception {
        log.info("preDestroyTestInstance");
    }

    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext context) {
        log.info("postProcessTestInstance");
    }
}
