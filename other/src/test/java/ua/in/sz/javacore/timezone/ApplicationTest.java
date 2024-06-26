package ua.in.sz.javacore.timezone;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;
import static ua.in.sz.javacore.timezone.Application.format;

@Slf4j
class ApplicationTest {

	@Test
	void zonedDateTime() {
		String userDirectory = System.getProperty("user.dir");
		log.info("Work dir: [{}]", userDirectory);

		log.info("Timezone: [{}]", format(TimeZone.getDefault()));

		ZonedDateTime dateTime = ZonedDateTime.now();
		log.info("Date time: [{}]", dateTime);

		TimeZone.setDefault(TimeZone.getTimeZone("Europe/Moscow"));
		log.info("Timezone: [{}]", format(TimeZone.getDefault()));

		ZonedDateTime dateTime1 = ZonedDateTime.now();
		log.info("Date time: [{}]", dateTime1);

		log.info("Date time: [{}]", dateTime);
	}

	@Test
	void dateTime() {
		log.info("Timezone: [{}]", format(TimeZone.getDefault()));

		LocalDateTime dateTime = LocalDateTime.of(2020, 1, 1, 0, 0, 0);
		log.info("Date time: [{}]", dateTime);

		TimeZone.setDefault(TimeZone.getTimeZone("Europe/Moscow"));
		log.info("Timezone: [{}]", format(TimeZone.getDefault()));

		LocalDateTime dateTime1 = LocalDateTime.of(2020, 1, 1, 0, 0, 0);
		log.info("Date time: [{}]", dateTime1);
	}

	@Test
	void dateTimeLipe() {
		LocalDateTime dateTime = LocalDateTime.of(2020, 2, 28, 0, 0, 0);
		LocalDateTime dateTime1 = dateTime.plusHours(24);
		LocalDateTime dateTime2 = dateTime.plusDays(1);
		LocalDateTime dateTime3 = dateTime.plus(Duration.ofDays(1));

		log.info("Date time: [{}] => [{}] => [{}] => [{}]", dateTime, dateTime1, dateTime2, dateTime3);
	}

	@Test
	void dateTimeLipe2() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		LocalDateTime dateTime = LocalDateTime.of(2015, 6, 30, 23, 59, 59);
		LocalDateTime dateTime1 = dateTime.plusSeconds(1);

		log.info("Date time: [{}] => [{}]", dateTime, dateTime1);
	}
}