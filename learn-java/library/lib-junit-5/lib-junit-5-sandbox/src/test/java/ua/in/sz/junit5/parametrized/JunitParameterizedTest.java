package ua.in.sz.junit5.parametrized;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

// https://youtrack.jetbrains.com/issue/IDEA-233324/Cannot-run-individual-JUnit-5-vintage-parametrized-test
// https://github.com/gradle/gradle/issues/19897
@Slf4j
public class JunitParameterizedTest {
    @SneakyThrows
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, -3, 15, Integer.MAX_VALUE})
    void isOdd_ShouldReturnTrueForOddNumbers(int number) {
//        TimeUnit.SECONDS.sleep(1);
        log.info("Hello {}", number);
    }
}
