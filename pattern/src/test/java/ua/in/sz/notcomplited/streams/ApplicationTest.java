package ua.in.sz.notcomplited.streams;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ApplicationTest {
	@Test
	void groupingBy() {
		final Dto[] dtos = {
				Dto.of(1, "One"),
				Dto.of(1, "One *"),
				Dto.of(2, "Two"),
				Dto.of(3, "Three"),
				Dto.of(3, "Three *")
		};

		Map<Integer, List<Dto>> index = Stream.of(dtos).collect(Collectors.groupingBy(Dto::getCode));

		index.forEach((key, value) -> System.out.println(String.format("%s=%s", key, value.size())));
	}
}