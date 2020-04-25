package ua.in.sz.refactoring.switchreplace;

import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.refactoring.switchreplace.model.ScheduleTypeVO;
import ua.in.sz.refactoring.switchreplace.model.ScheduleVO;
import ua.in.sz.refactoring.switchreplace.model.TypeCode;
import ua.in.sz.refactoring.switchreplace.resolver.IdentificationResolver;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class Application {

	public static void main(String[] args) {
		List<ScheduleVO> schedules = ImmutableList.of(
				ScheduleVO.builder()
						.scheduleType(ScheduleTypeVO.builder().code(TypeCode.T1.name()).name("Type 1").build())
						.identification("Id 1")
						.build(),
				ScheduleVO.builder()
						.scheduleType(ScheduleTypeVO.builder().code(TypeCode.T2.name()).name("Type 2").build())
						.identification("Id 2")
						.build(),
				ScheduleVO.builder()
						.scheduleType(ScheduleTypeVO.builder().code("Unknown").name("Type 2").build())
						.identification("Id 2")
						.build()
		);

		IdentificationResolver resolver = new IdentificationResolver();

		String result = schedules.stream().map(resolver::resolve).collect(Collectors.joining(", "));

		log.info("Result: {}", result);
	}
}
