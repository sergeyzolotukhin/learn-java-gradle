package ua.in.sz.unit.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@Slf4j
@ExtendWith(RandomParametersExtension.class)
public class RandomParametersExtensionTests {
    @Test
    void injectsDouble(@RandomParametersExtension.Random double d) {
        log.info("D {}", d);
    }
}
