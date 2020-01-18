package ua.in.sz.notcomplited.schedules.factory;

import lombok.experimental.UtilityClass;
import org.joda.time.DateTime;
import org.joda.time.Period;
import ua.in.sz.notcomplited.schedules.domain.Resolution;
import ua.in.sz.notcomplited.schedules.domain.ScheduleVO;
import ua.in.sz.notcomplited.schedules.util.Dates;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@UtilityClass
public final class ScheduleVOFactory {

	public static ScheduleVO create(DateTime start, Resolution resolution) {
		DateTime end = start.plus(resolution.period());

		return ScheduleVO.builder()
				.start(start)
				.end(end)
				.resolution(resolution)
				.build();
	}

	public static List<ScheduleVO> generate(DateTime start, DateTime end, Resolution resolution) {
		Period period = resolution.period();
		long periods = Dates.periods(start, end, period);

		return Stream.iterate(start, d -> d.plus(period)).limit(periods)
				.map(d -> create(d, resolution))
				.collect(toList());
	}


}
