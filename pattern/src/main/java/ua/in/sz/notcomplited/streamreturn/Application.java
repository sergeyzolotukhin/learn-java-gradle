package ua.in.sz.notcomplited.streamreturn;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import ua.in.sz.notcomplited.streamreturn.domain.ScheduleVO;
import ua.in.sz.notcomplited.streamreturn.util.Dates;
import ua.in.sz.notcomplited.streamreturn.util.ScheduleVOs;

import java.util.List;

import static ua.in.sz.notcomplited.streamreturn.domain.Resolution.P1D;
import static ua.in.sz.notcomplited.streamreturn.domain.Resolution.PT15M;

@Slf4j
public class Application {

	public static void main(String[] args) {
		DateTime start = Dates.dateTime("2020-01-18 00:00");
		DateTime end = start.plus(P1D.period());

		List<ScheduleVO> schedules = ScheduleVOs.generate(start, end, PT15M.period());

		log.info("Schedule count {}:", schedules.size());

		for (ScheduleVO schedule : schedules) {
			log.info("\t{}", schedule);
		}
	}
}
