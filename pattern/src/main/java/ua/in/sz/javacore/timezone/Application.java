package ua.in.sz.javacore.timezone;

import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * https://docs.oracle.com/javase/7/docs/api/java/util/Formatter.html
 */
@Slf4j
public class Application {
	public static void main(String[] args) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

		ZonedDateTime dateTime = ZonedDateTime.of(2020, 3, 28,
				12, 0, 0, 0,
				TimeZone.getTimeZone("Europe/Kiev").toZoneId());

		for (int i = 0; i < 24; i++) {
			ZonedDateTime dateTime1 = dateTime.plusHours(i);
			log.info("Date time: [{}] => [{}]", formatter.format(dateTime1), dateTime1);
		}
	}

	private static void method1() {
		log.info("Timezone: [{}]", format(TimeZone.getDefault()));

//		LocalDateTime dateTime = LocalDateTime.of(2020, 1, 1, 0, 0, 0);
//		LocalDateTime dateTime = LocalDateTime.now();
		ZonedDateTime dateTime = ZonedDateTime.now();
		log.info("Date time: [{}]", dateTime);

		TimeZone.setDefault(TimeZone.getTimeZone("Europe/Moscow"));
		log.info("Timezone: [{}]", format(TimeZone.getDefault()));

//		LocalDateTime dateTime1 = LocalDateTime.of(2020, 1, 1, 0, 0, 0);
//		LocalDateTime dateTime1 = LocalDateTime.now();
		ZonedDateTime dateTime1 = ZonedDateTime.now();
		log.info("Date time: [{}]", dateTime1);
	}

	public static void help() {
		Arrays.stream(TimeZone.getAvailableIDs()).forEach(id ->
				log.info("Timezone [{}]", format(TimeZone.getTimeZone(id))));
	}

	public static String format(TimeZone tz) {
		long hours = TimeUnit.MILLISECONDS.toHours(tz.getRawOffset());
		long minutes = TimeUnit.MILLISECONDS.toMinutes(tz.getRawOffset()) - TimeUnit.HOURS.toMinutes(hours);

		return String.format("%20.20s | %3d:%3d", tz.getID(), hours, minutes);
	}
}
