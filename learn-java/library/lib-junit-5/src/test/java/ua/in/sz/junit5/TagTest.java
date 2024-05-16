package ua.in.sz.junit5;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("my-tag")
@Slf4j
class TagTest {

    public TagTest() {
        log.info("MainTest");
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