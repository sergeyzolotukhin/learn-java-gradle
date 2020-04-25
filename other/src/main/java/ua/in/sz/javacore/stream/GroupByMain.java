package ua.in.sz.javacore.stream;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class GroupByMain {
	public static void main(String[] args) {
		List<GroupDto> cases = new ArrayList<>();

		cases.add(GroupDto.builder().type("ISP1").date(LocalDate.of(2020, 1, 1)).build());
		cases.add(GroupDto.builder().type("ISP1").date(LocalDate.of(2020, 1, 3)).build());
		cases.add(GroupDto.builder().type("ISP1").date(LocalDate.of(2020, 1, 3)).build());


		Map<String, Optional<GroupDto>> result = cases.stream().collect(Collectors.groupingBy(GroupDto::getType,
				Collectors.maxBy(Comparator.comparing(GroupDto::getDate))));

		log.info("End {}", result.get("ISP1"));
	}
}
