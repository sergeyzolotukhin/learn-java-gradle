package ua.in.sz.junit5;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@Slf4j
public class JUnit5NestedExampleTest {

    @Test
    void test_A() {
        log.info("Example test for method A");
    }

    @Test
    void test_B() {
        log.info("Example test for method A");
    }

    @Nested
    class A {
        @Test
        void test_A() {
            log.info("Example test for method A");
        }

        @Test
        void test_B() {
            log.info("Example test for method A");
        }
    }

    @Nested
    class B {
        @Test
        void test_A() {
            log.info("Example test for method A");
        }

        @Test
        void test_B() {
            log.info("Example test for method A");
        }
    }
}
