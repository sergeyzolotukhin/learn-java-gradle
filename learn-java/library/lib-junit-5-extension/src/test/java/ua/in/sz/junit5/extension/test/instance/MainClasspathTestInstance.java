package ua.in.sz.junit5.extension.test.instance;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

@Slf4j
@ExtendWith(JunitClasspathExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MainClasspathTestInstance {

    public MainClasspathTestInstance() {
//        log.info("MainTest");
    }

    @Test
    void main_1() {
        log.info("main 1 {}", this);
    }

    @Test
    void main_2() {
        log.info("main 2 {}", this);
    }
}