package ua.in.sz.unit.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
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

    /*
    https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests-display-names
     */

    @MethodSource
    @ParameterizedTest(name = "Parametrized test name other [{0}], [{1}]")
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

    @MethodSource
    @ParameterizedTest(name = "Parametrized test name [{0}]")
    void testWithArgument(String name) {
        log.info("[{}]", name);
    }

    private static Stream<String> testWithArgument() {
        return Stream.of(null, "", "  ");
    }

    @ParameterizedTest
    @ArgumentsSource(BlankStringsArgumentsProvider.class)
    void isBlank_ShouldReturnTrueForNullOrBlankStringsArgProvider(String name) {
        log.info("[{}]", name);
    }

    static class BlankStringsArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of((String) null),
                    Arguments.of(""),
                    Arguments.of("   ")
            );
        }
    }

    static Stream<Arguments> arguments = Stream.of(
            Arguments.of(null, true), // null strings should be considered blank
            Arguments.of("", true),
            Arguments.of("  ", true),
            Arguments.of("not blank", false)
    );

    @ParameterizedTest
    @VariableSource("arguments")
    void isBlank_ShouldReturnTrueForNullOrBlankStringsVariableSource(String name, boolean expected) {
        log.info("[{}] [{}]", name, expected);
    }
}