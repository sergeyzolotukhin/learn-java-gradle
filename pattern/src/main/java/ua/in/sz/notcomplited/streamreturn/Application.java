package ua.in.sz.notcomplited.streamreturn;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ua.in.sz.notcomplited.streamreturn.Resolution.*;

@Slf4j
public class Application {
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");

	public static void main(String[] args) {
		DateTime start = dateTime("2020-01-18 23:00");

		Period period = PT15M.period();

		long periods = periods(start, start.plus(PT1H.period()), period);

		List<ScheduleVO> schedules = Stream.iterate(start, d -> d.plus(period)).limit(periods)
				.map(d -> ScheduleVO.builder().start(d).end(d.plus(period)).build())
				.collect(Collectors.toList());

		log.info("Schedule count {}:", periods);

		for (ScheduleVO schedule : schedules) {
			log.info("\t{}", schedule);
		}
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

	@SuppressWarnings("SameParameterValue")
	private static DateTime dateTime(String string) {
		return DateTime.parse(string, DATE_TIME_FORMATTER);
	}
}
