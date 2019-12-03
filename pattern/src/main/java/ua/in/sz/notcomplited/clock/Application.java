package ua.in.sz.notcomplited.clock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
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

		LocalDateTime current = LocalDate.of(2019, 11, 10).atStartOfDay();

		for (int i = 0; i < 31*2*24; i++) {
			current = current.plus(4, ChronoUnit.HOURS);

			clock = Clock.fixed(current.toInstant(ZoneOffset.UTC), ZoneId.systemDefault());

			final LocalDateTime c = current;
//			log.info("Current: {}", c);
			processes.stream()
					.filter(Application::isDueDateNow)
					.forEach(p -> log.info("Process current {} due date: {}", c, p.getDueDate()));
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
