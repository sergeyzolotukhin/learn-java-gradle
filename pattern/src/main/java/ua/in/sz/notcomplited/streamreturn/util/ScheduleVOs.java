package ua.in.sz.notcomplited.streamreturn.util;

import lombok.experimental.UtilityClass;
import org.joda.time.DateTime;
import org.joda.time.Period;
import ua.in.sz.notcomplited.streamreturn.domain.ScheduleVO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@UtilityClass
public final class ScheduleVOs {
	public static List<ScheduleVO> generate(DateTime start, DateTime end, Period period) {
		long periods = periods(start, end, period);

		return Stream.iterate(start, d -> d.plus(period)).limit(periods)
				.map(d -> ScheduleVO.builder().start(d).end(d.plus(period)).build())
				.collect(Collectors.toList());
	}

	private static long periods(DateTime start, DateTime end, Period period) {
		long count = 0;

		DateTime d = start;
		while (d.isBefore(end)) {
			count++;
			d = d.plus(period);
		}

		return count;
	}
}
