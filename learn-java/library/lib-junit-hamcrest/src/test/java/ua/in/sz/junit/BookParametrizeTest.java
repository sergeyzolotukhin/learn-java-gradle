package ua.in.sz.junit;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

@Slf4j
class BookParametrizeTest {

	@ParameterizedTest
	@CsvFileSource(resources = "/books.csv", numLinesToSkip = 1)
	void find(String title) {
		log.info("Book: {}", title);
	}

	@ParameterizedTest
	@ValueSource(ints = { 1, 3, 8 })
	void testWithValueSource(int argument) {
		log.info("Execute: {}", argument);
	}

	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = { "string 1", "string 2"})
	void nullEmptyAndBlankStrings(String text) {
		log.info("Text: {}", text);
	}

	@ParameterizedTest
	@MethodSource("stringProvider")
	void testWithExplicitLocalMethodSource(String argument) {
		log.info("Method source: {}", argument);
	}

	static Stream<String> stringProvider() {
		return Stream.of("apple", "banana");
	}

	@ParameterizedTest
	@MethodSource
	void testWithDefaultLocalMethodSource(String argument) {
		log.info("Method source by convention: {}", argument);
	}

	@SuppressWarnings("unused")
	public static Stream<String> testWithDefaultLocalMethodSource() {
		return Stream.of("apple", "banana");
	}

	@ParameterizedTest
	@CsvSource({
			"apple,         1",
			"banana,        2",
			"'lemon, lime', 0xF1"
	})
	void testWithCsvSource(String fruit, int rank) {
		log.info("Csv source: {} = {}", fruit, rank);
	}
}
