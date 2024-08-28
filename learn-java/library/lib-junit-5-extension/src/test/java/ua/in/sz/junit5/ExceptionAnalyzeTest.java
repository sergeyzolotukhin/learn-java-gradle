package ua.in.sz.junit5;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@Slf4j
@ExtendWith(ExceptionHandlerExtension.class)
class ExceptionAnalyzeTest {

    @Test
    void main_1() {
        throw new IllegalStateException("My exception");
    }
}