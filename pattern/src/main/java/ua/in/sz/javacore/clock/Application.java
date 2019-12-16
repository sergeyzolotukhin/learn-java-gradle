package ua.in.sz.javacore.clock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Application {
	private static List<Process> processes = new ArrayList<>();

	private static Clock clock = Clock.systemDefaultZone();

	public static void main(String[] args) {
		processes.add(new Process(LocalDate.of(2019, 11, 15)));
		processes.add(new Process(LocalDate.of(2019, 12, 3)));
		processes.add(new Process(LocalDate.of(2019, 12, 15)));

		LocalDateTime start = LocalDate.of(2019, 11, 10).atStartOfDay();
		Clock baseClock = Clock.fixed(start.toInstant(ZoneOffset.UTC), ZoneId.systemDefault());

		for (int i = 0; i < 31 * 2 * 24; i++) {
			clock = Clock.offset(baseClock, Duration.ofHours(4 * i));

			processes.stream()
					.filter(Application::isDueDateNow)
					.forEach(p -> log.info("Process current {} due date: {}", LocalDateTime.now(clock), p.getDueDate()));
		}
	}

	private static boolean isDueDateNow(Process process) {
		LocalDateTime from = process.getDueDate().atStartOfDay();
		LocalDateTime to = from.plusDays(1);

		LocalDateTime now = LocalDateTime.now(clock);

		return (now.isEqual(from) || now.isAfter(from))
				&& now.isBefore(to);
	}

	@Getter
	@AllArgsConstructor
	private static class Process {
		private LocalDate dueDate;
	}
}
