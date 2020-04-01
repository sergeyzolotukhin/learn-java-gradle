package ua.in.sz.javacore.timezone;

import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Europe/Kiev:
 * 		[29-03-2020 02:00:00] => [2020-03-29T02:00+02:00[Europe/Kiev]]
 * 		[29-03-2020 04:00:00] => [2020-03-29T04:00+03:00[Europe/Kiev]]
 */
@Slf4j
public class DstApplication {
	public static void main(String[] args) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

		ZonedDateTime dateTime = ZonedDateTime.of(2020, 3, 29,
				0, 0, 0, 0,
				TimeZone.getTimeZone("Europe/Kiev").toZoneId());

		for (int i = 0; i < 4; i++) {
			ZonedDateTime dateTime1 = dateTime.plusHours(i);
			log.info("Date time: [{}] => [{}]", formatter.format(dateTime1), dateTime1);
		}
	}
}
