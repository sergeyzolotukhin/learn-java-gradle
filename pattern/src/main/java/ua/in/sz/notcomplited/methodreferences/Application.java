package ua.in.sz.notcomplited.methodreferences;

import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.joining;

@Slf4j
public class Application {
	public static void main(String[] args) {
		Function<NamedVO, String> nameProvider = NamedVO::getName;

		List<NamedVO> namedVOs = ImmutableList.of(
				NamedVO.builder().name("Name One").build(),
				NamedVO.builder().name("Name Two").build()
		);

		String names = namedVOs.stream()
				.map(nameProvider)
				.collect(joining(", "));

		log.info("Names: {}", names);
	}

	@Getter
	@Builder
	private static class NamedVO {
		private String name;
	}
}
