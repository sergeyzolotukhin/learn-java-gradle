package ua.in.sz.notcomplited.schedules.util;

import lombok.experimental.UtilityClass;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@UtilityClass
public final class Dates {
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");

	public static DateTime dateTime(String string) {
		return DateTime.parse(string, DATE_TIME_FORMATTER);
	}

	public static long periods(DateTime start, DateTime end, Period period) {
		long count = 0;

		DateTime d = start;
		while (d.isBefore(end)) {
			count++;
			d = d.plus(period);
		}

		return count;
	}
}
