package ua.in.sz.junit5;

import lombok.extern.slf4j.Slf4j;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

@Slf4j
class MainTest {

    @Test
    void main_1() {
        BigDecimal[] list = Instancio.stream(BigDecimal.class)
                .limit(5)
                .toArray(BigDecimal[]::new);

        log.info("array size {}: {}", list.length, list[0]);
    }

}