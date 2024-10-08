package ua.in.sz.notcomplited.streams;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ApplicationTest {

	Dto[] dtos;

	@BeforeEach
	private void setup() {
		dtos = new Dto[]{
				Dto.of(1, "One"),
				Dto.of(1, "One *"),
				Dto.of(2, "Two"),
				Dto.of(3, "Three"),
				Dto.of(3, "Three *")};
	}

	@Test
	void groupingBy() {
		Map<Integer, List<Dto>> index = Stream.of(dtos).collect(Collectors.groupingBy(Dto::getCode));

		index.forEach((key, value) -> System.out.printf("%s=%s%n", key, value.size()));
	}

	@Test
	void partitioningBy() {
		Map<Boolean, List<Dto>> partitions = Stream.of(dtos).collect(Collectors.partitioningBy(i -> i.getCode() <= 2));

		partitions.forEach((key, value) -> System.out.printf("%s=%s%n", key, value.size()));
	}

	@Test
	void summarizingInt() {
		IntSummaryStatistics sum = Stream.of(dtos).collect(Collectors.summarizingInt(Dto::getCode));

		System.out.println("Sum:" + sum);
	}

	@Test
	void partitioningBySize() {
		AtomicInteger counter = new AtomicInteger();

		Collection<List<Dto>> partitions = Stream.of(dtos)
				.collect(Collectors.groupingBy(it -> counter.getAndIncrement() / 2))
				.values();

		partitions.forEach((values) -> System.out.printf("%s%n", values.size()));
	}
}