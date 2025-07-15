package ua.in.sz.junit5;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

@Slf4j
class SandboxTest {

    public SandboxTest() {
//        log.info("MainTest");
    }

    @Test
    void main_0() {
        log.info("MainTest 1");
        System.err.println("MainTest 2");
        log.info("MainTest 3");
    }

    @Test
    void main_1() {
        Assumptions.assumeTrue(false, "Assumptions");
        log.info("main 1 {}", this);
    }

    @Test
    void main_2() {
        log.info("main 2 {}", this);
    }

    @Test
    void main_3() {
        log.info("main 3 {}", this);
    }
}