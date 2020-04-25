package ua.in.sz.notcomplited.schedules.logging;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import ua.in.sz.notcomplited.schedules.domain.ScheduleVO;
import ua.in.sz.notcomplited.schedules.domain.ScheduleValueVO;

@UtilityClass
public class Logs {
	private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormat.forPattern("yy.MM.dd HH:mm");
	private static final DateTimeFormatter TIME_FORMAT = DateTimeFormat.forPattern("HH:mm");

	public static void log(Logger logger, ScheduleVO scheduleVO) {

		String from = DATE_TIME_FORMAT.print(scheduleVO.getStart());
		String to = DATE_TIME_FORMAT.print(scheduleVO.getEnd());

		String H_LINE = StringUtils.rightPad("=", 120, "=");

		logger.info("Schedule [{} - {}]", from, to);

		logger.info("{}", H_LINE);

		for (ScheduleValueVO<?> value : scheduleVO.getValues()) {
			String vs = TIME_FORMAT.print(value.getStart());

			logger.info("|{}|{}|", vs, StringUtils.leftPad("" + value.getValue(), 30));
		}

		logger.info("{}", H_LINE);
	}
}
