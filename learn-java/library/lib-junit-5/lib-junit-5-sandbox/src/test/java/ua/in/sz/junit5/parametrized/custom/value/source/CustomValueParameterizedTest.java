package ua.in.sz.junit5.parametrized.custom.value.source;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import ua.in.sz.junit5.parametrized.custom.value.source.impl.CustomValueSource;

@Slf4j
public class CustomValueParameterizedTest {

    @ParameterizedTest
    @CustomValueSource({"1,2,3,4", "10,11", "21,22"})
    void myParameterizedTest(String arg) {
        log.info("{}", arg);
    }
}
