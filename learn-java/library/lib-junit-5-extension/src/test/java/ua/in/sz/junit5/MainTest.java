package ua.in.sz.junit5;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@Slf4j
@ExtendWith(JunitExtension.class)
class MainTest {

    public MainTest() {
        log.info("MainTest");
    }

    @BeforeAll
    static void initAll() {
        log.info("BeforeAll");
    }

    @BeforeEach
    void init() {
        log.info("BeforeEach {}", this);
    }

    @AfterEach
    void tearDown() {
        log.info("AfterEach {}", this);
    }

    @AfterAll
    static void tearDownAll() {
        log.info("AfterAll");
    }

    @Test
    void main_1() {
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