package ua.in.sz.unit.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class MultipleAssertsTest {

    @Test
    @Disabled
    void multipleSeparateAsserts() {
        int a = 1;
        int b = 2;
        int e = 3;

        assertEquals(e, a);
        assertEquals(e, b);
    }

    @Test
    @Disabled
    void multipleAllAsserts() {
        int a = 1;
        int b = 2;
        int c = 3;
        int e = 3;

        assertAll(
                "Grouped Assertions of User",
                () -> assertEquals(e, a, "Username should be admin"),
                () -> assertEquals(e, b, "Email should be admin@baeldung.com"),
                () -> assertEquals(e, c, "User should be activated")
        );
    }

    @ParameterizedTest
    @MethodSource
    void testParametrized(String name, boolean expected) {
        log.info("[{}] [{}]", name, expected);
    }

    private static Stream<Arguments> testParametrized() {
        return Stream.of(
                Arguments.of(null, true),
                Arguments.of("", true),
                Arguments.of("  ", true),
                Arguments.of("not blank", false)
        );
    }

    @ParameterizedTest
    @MethodSource
    void testWithArgument(String name) {
        log.info("[{}]", name);
    }

    private static Stream<String> testWithArgument() {
        return Stream.of(null, "", "  ");
    }
}