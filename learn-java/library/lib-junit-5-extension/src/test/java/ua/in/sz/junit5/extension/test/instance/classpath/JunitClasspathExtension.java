package ua.in.sz.junit5.extension.test.instance.classpath;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstanceFactoryContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;
import org.junit.jupiter.api.extension.TestInstancePreConstructCallback;
import org.junit.jupiter.api.extension.TestInstancePreDestroyCallback;

@Slf4j
public class JunitClasspathExtension
        implements BeforeAllCallback, AfterAllCallback,
        TestInstancePreConstructCallback,
        TestInstancePostProcessor,
        TestInstancePreDestroyCallback {

    private static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(JunitClasspathExtension.class);

    private static final String KEY = "DefaultLocale";

    @Override
    public void beforeAll(ExtensionContext context) {
        if (context.getTestInstanceLifecycle().orElse(TestInstance.Lifecycle.PER_METHOD)
                .equals(TestInstance.Lifecycle.PER_METHOD)) {
            log.info("set class path");
            context.getStore(NAMESPACE).put(KEY, "beforeAll");
        }
    }

    @Override
    public void afterAll(ExtensionContext context) {
        if (context.getTestInstanceLifecycle().orElse(TestInstance.Lifecycle.PER_METHOD)
                .equals(TestInstance.Lifecycle.PER_METHOD)) {
            String value = context.getStore(NAMESPACE).get(KEY, String.class);
            log.info("restore class path: {}", value);
        }
    }

    @Override
    public void preConstructTestInstance(TestInstanceFactoryContext factoryContext, ExtensionContext context) throws Exception {
        if (context.getTestInstanceLifecycle().orElse(TestInstance.Lifecycle.PER_METHOD)
                .equals(TestInstance.Lifecycle.PER_CLASS)) {
            log.info("set class path");
            context.getStore(NAMESPACE).put(KEY, "preConstructTestInstance");
        }
    }

    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext context) {
        log.info("use class path");
    }

    @Override
    public void preDestroyTestInstance(ExtensionContext context) throws Exception {
        if (context.getTestInstanceLifecycle().orElse(TestInstance.Lifecycle.PER_METHOD)
                .equals(TestInstance.Lifecycle.PER_CLASS)) {
            String value = context.getStore(NAMESPACE).get(KEY, String.class);
            log.info("restore class path: {}", value);
        }
    }

}
