package ua.in.sz.refactoring.switchreplace.resolver;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.refactoring.switchreplace.model.ScheduleVO;
import ua.in.sz.refactoring.switchreplace.model.TypeCode;

import java.util.Map;
import java.util.function.Function;

@Slf4j
public class IdentificationResolver {

	public String resolve(ScheduleVO schedule) {
		return defaultResolvers()
				.getOrDefault(schedule.getScheduleType().getCode(), this::unsupportedScheduleType)
				.apply(schedule);
	}

	// ================================================================================================================
	// private methods
	// ================================================================================================================

	private Map<String, Function<ScheduleVO, String>> defaultResolvers() {
		return ImmutableMap.of(
				TypeCode.T1.name(), this::resolveImbalanceIdentification,
				TypeCode.T2.name(), this::resolveAvailabilityIdentification
		);
	}

	private String resolveAvailabilityIdentification(ScheduleVO schedule) {
		return schedule.getIdentification();
	}

	private String resolveImbalanceIdentification(ScheduleVO schedule) {
		return schedule.getScheduleType().getName();
	}

	private String unsupportedScheduleType(ScheduleVO schedule) {
		return String.format("Unsupported schedule type [%s] with identification [%s]",
				schedule.getScheduleType().getName(), schedule.getIdentification());
	}
}
