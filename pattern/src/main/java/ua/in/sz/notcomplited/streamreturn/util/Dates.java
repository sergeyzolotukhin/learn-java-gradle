package ua.in.sz.notcomplited.streamreturn.util;

import lombok.experimental.UtilityClass;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@UtilityClass
public final class Dates {
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");

	public static DateTime dateTime(String string) {
		return DateTime.parse(string, DATE_TIME_FORMATTER);
	}
}
