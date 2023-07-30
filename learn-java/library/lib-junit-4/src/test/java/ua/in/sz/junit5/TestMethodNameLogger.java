package ua.in.sz.junit5;

import lombok.extern.slf4j.Slf4j;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

@Slf4j
public class TestMethodNameLogger implements TestRule {

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                log.info("Before test {}", description);
                try {
                    base.evaluate();
                } finally {
                    log.info("After test {}", description);
                }
            }
        };
    }
}
