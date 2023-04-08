package ua.in.sz.junit5;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@Slf4j
@ExtendWith(JunitExtension.class)
class MainTwoTest {

    @Test
    void main_1() {
        log.info("main 1");
    }

    @Test
    void main_2() {
        log.info("main 2");
    }

    @Test
    void main_3() {
        log.info("main 3");
    }
}