package ua.in.sz.contex.resolver;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.contex.resolver.model.Interval;
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
					.interval(Interval.builder()
							.from(dateTime.plusHours(i))
							.to(dateTime.plusHours(i + 1)).build())
					.value(i)
					.build());
		}


		Schedule caseSchedule = Schedule.builder()
				.interval(Interval.builder()
						.from(marketDate.atStartOfDay())
						.to(marketDate.plusDays(1).atStartOfDay()).build())
				.values(values)
				.build();

		Schedule schedule = Schedule.builder()
				.interval(boundValueInterval(caseSchedule))
				.values(caseSchedule.getValues()).build();

		print(schedule);
	}

	public static void print(Schedule schedule) {
		log.info("{}", schedule);

		for (ScheduleValue value : schedule.getValues()) {
			log.info(" |-> {}", value);
		}
	}

	public static Interval boundValueInterval(Schedule schedule) {
		LocalDateTime from = schedule.getValues().stream()
				.map(ScheduleValue::getInterval)
				.map(Interval::getFrom)
				.min(Comparator.naturalOrder())
				.orElseThrow(IllegalStateException::new);

		LocalDateTime to = schedule.getValues().stream()
				.map(ScheduleValue::getInterval)
				.map(Interval::getTo)
				.max(Comparator.naturalOrder())
				.orElseThrow(IllegalStateException::new);

		return Interval.builder().from(from).to(to).build();
	}
}
