package ua.in.sz.javacore.timezone;

import lombok.extern.slf4j.Slf4j;

import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * https://docs.oracle.com/javase/7/docs/api/java/util/Formatter.html
 */
@Slf4j
public class Application {
	public static void main(String[] args) {
		String[] ids = TimeZone.getAvailableIDs();
/*
		for (String id : ids) {
			log.info("Timezone [{}]", displayTimeZone(TimeZone.getTimeZone(id)));
		}
*/

		log.info("Timezone: [{}]", displayTimeZone(TimeZone.getDefault()));

//		TimeZone.setDefault(TimeZone.getTimeZone("America/Los_Angeles"));
	}

	private static String displayTimeZone(TimeZone tz) {
		long hours = TimeUnit.MILLISECONDS.toHours(tz.getRawOffset());
		long minutes = TimeUnit.MILLISECONDS.toMinutes(tz.getRawOffset()) - TimeUnit.HOURS.toMinutes(hours);

		return String.format("%20.20s | %3d:%3d",  tz.getID(),  hours, minutes);
	}
}
