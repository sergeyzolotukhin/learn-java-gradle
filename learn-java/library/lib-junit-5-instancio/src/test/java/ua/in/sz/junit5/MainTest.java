package ua.in.sz.junit5;

import lombok.extern.slf4j.Slf4j;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
class MainTest {

    @Test
    void main_1() {
        List<BigDecimal> list = Instancio.stream(BigDecimal.class)
                .limit(5)
                .toList();
        log.info("main 1 {}", list);
    }

}