package ua.in.sz.javacore.comparatorchain;

import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class Application {
	public static void main(String[] args) {
		List<Dto> list = ImmutableList.of(
				Dto.builder().name("B1").code("C1").build(),
				Dto.builder().name("A1").code("C2").build(),
				Dto.builder().name("A1").code("C1").build()
		);

		String result = list.stream()
				.sorted(Comparator.comparing(Dto::getName).thenComparing(Dto::getCode))
				.map(Dto::toString)
				.collect(Collectors.joining(", "));

		log.info("Sorted: {}", result);
	}

	@Builder
	@Getter
	@ToString
	private static class Dto {
		private String name;
		private String code;
	}
}
