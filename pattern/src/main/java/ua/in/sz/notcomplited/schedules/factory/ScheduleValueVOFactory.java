package ua.in.sz.notcomplited.schedules.factory;

import lombok.experimental.UtilityClass;
import ua.in.sz.notcomplited.schedules.domain.ScheduleVO;
import ua.in.sz.notcomplited.schedules.domain.ScheduleValueVO;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@UtilityClass
public final class ScheduleValueVOFactory {
	public static List<ScheduleValueVO<?>> generate(ScheduleVO schedule, Object... values) {
		return Arrays.stream(values)
				.map(v -> create(schedule, v))
				.collect(toList());
	}

	private static ScheduleValueVO<?> create(ScheduleVO schedule, Object value) {
		return ScheduleValueVO.builder()
				.start(schedule.getStart())
				.end(schedule.getEnd())
				.schedule(schedule)
				.value(value)
				.build();
	}
}
