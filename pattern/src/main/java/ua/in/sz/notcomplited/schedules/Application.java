package ua.in.sz.notcomplited.schedules;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import ua.in.sz.notcomplited.schedules.domain.ScheduleVO;
import ua.in.sz.notcomplited.schedules.domain.ScheduleValueVO;
import ua.in.sz.notcomplited.schedules.util.Dates;
import ua.in.sz.notcomplited.schedules.util.ScheduleVOFactory;
import ua.in.sz.notcomplited.schedules.util.ScheduleValueVOFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static ua.in.sz.notcomplited.schedules.domain.Resolution.P1D;
import static ua.in.sz.notcomplited.schedules.domain.Resolution.PT15M;
import static ua.in.sz.notcomplited.schedules.domain.Resolution.PT1H;

@Slf4j
public class Application {

	public static void main(String[] args) {
		DateTime start = Dates.dateTime("2020-01-18 00:00");
		DateTime end = start.plus(PT1H.period());

		ScheduleVO schedule = ScheduleVOFactory.create(start, P1D);

		List<ScheduleValueVO<?>> numberValues = ScheduleValueVOFactory.generate(schedule, 1, 2, 3, 5, null);
		List<ScheduleValueVO<?>> stringValues = ScheduleValueVOFactory.generate(schedule, "One", null, "Two", "Three");

		schedule.setValues(Stream.concat(numberValues.stream(), stringValues.stream()).collect(toList()));

		log.info("Schedule: {}", schedule);
		log.info("Schedule values [count={}] :", schedule.getValues().size());
		schedule.getValues().forEach(v -> log.info("\t{}", v));

		List<ScheduleVO> schedules = ScheduleVOFactory.generate(start, end, PT15M);

		log.info("Schedule [count={}]:", schedules.size());
		schedules.forEach(s -> log.info("\t{}", s));
	}
}
