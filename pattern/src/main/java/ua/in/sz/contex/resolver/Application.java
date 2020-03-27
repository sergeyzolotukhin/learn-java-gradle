package ua.in.sz.contex.resolver;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.contex.resolver.model.Schedule;
import ua.in.sz.contex.resolver.model.ScheduleValue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
public class Application {
	public static void main(String[] args) {

		LocalDate marketDate = LocalDate.of(2020, 1, 1);

		LocalDateTime dateTime = marketDate.atStartOfDay();
		List<ScheduleValue> values = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			values.add(ScheduleValue.builder()
					.from(dateTime.plusHours(i))
					.to(dateTime.plusHours(i + 1))
					.value(i)
					.build());
		}


		Schedule caseSchedule = Schedule.builder()
				.from(marketDate.atStartOfDay())
				.to(marketDate.plusDays(1).atStartOfDay())
				.values(values)
				.build();

		print(boundValueSchedule(caseSchedule));
	}

	public static void print(Schedule schedule) {
		log.info("{}", schedule);

		for (ScheduleValue value : schedule.getValues()) {
			log.info(" |-> {}", value);
		}
	}

	public static Schedule boundValueSchedule(Schedule schedule) {
		LocalDateTime from = schedule.getValues().stream()
				.map(ScheduleValue::getFrom)
				.min(Comparator.naturalOrder())
				.orElseThrow(IllegalStateException::new);

		LocalDateTime to = schedule.getValues().stream()
				.map(ScheduleValue::getTo)
				.max(Comparator.naturalOrder())
				.orElseThrow(IllegalStateException::new);

		return Schedule.builder().from(from).to(to).values(schedule.getValues()).build();
	}
}
