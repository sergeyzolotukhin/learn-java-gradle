package ua.in.sz.notcomplited.schedules;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import ua.in.sz.notcomplited.schedules.domain.ScheduleVO;
import ua.in.sz.notcomplited.schedules.domain.ScheduleValueVO;
import ua.in.sz.notcomplited.schedules.builder.ScheduleValueVOListBuilder;
import ua.in.sz.notcomplited.schedules.util.Dates;
import ua.in.sz.notcomplited.schedules.factory.ScheduleVOFactory;
import ua.in.sz.notcomplited.schedules.logging.Logs;

import java.util.List;

import static ua.in.sz.notcomplited.schedules.domain.Resolution.P1D;
import static ua.in.sz.notcomplited.schedules.domain.Resolution.PT15M;
import static ua.in.sz.notcomplited.schedules.domain.Resolution.PT1H;

@Slf4j
public class Application {

	public static void main(String[] args) {
		DateTime start = Dates.dateTime("2020-01-18 00:00");
		DateTime end = start.plus(PT1H.period());

		ScheduleVO schedule = ScheduleVOFactory.create(start, P1D);

		List<ScheduleValueVO<?>> values = ScheduleValueVOListBuilder.builder()
				.schedule(schedule)
				.values(1, 2, 3, 5, null, "One", true, "Two", "Three")
				.build();

		schedule.setValues(values);

		log.info("Schedule: {}", schedule);
		log.info("Schedule values [count={}] :", schedule.getValues().size());
		schedule.getValues().forEach(v -> log.info("\t{}", v));

		List<ScheduleVO> schedules = ScheduleVOFactory.generate(start, end, PT15M);

		log.info("Schedule [count={}]:", schedules.size());
		schedules.forEach(s -> log.info("\t{}", s));

		Logs.log(log, schedule);
	}
}
