package ua.in.sz.javacore.time;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Slf4j
public class Main {
	public static void main(String[] args) {
		LocalDate date = LocalDate.of(2020, 1, 18);
		LocalTime time = LocalTime.of(6, 14);

		LocalDateTime dateTime = LocalDateTime.of(date, time);

		log.info("Date [{}], time [{}], date time [{}]", date, time, dateTime);
	}
}
