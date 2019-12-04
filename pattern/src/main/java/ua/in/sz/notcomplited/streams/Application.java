package ua.in.sz.notcomplited.streams;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.Stream;

@Slf4j
public class Application {
	public static void main(String[] args) {
		Integer sum = Stream.iterate(1, (i) -> ++i)
				.limit(20)
				.peek(i -> log.info("item: {}", i))
				.reduce(0, Integer::sum);

		log.info("Sum: {}", sum);
	}
}
