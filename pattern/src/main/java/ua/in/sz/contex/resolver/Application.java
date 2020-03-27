package ua.in.sz.contex.resolver;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.contex.resolver.model.Schedule;
import ua.in.sz.contex.resolver.model.ScheduleValue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

		print(caseSchedule);
	}

	public static void print(Schedule schedule) {
		log.info("{}", schedule);

		for (ScheduleValue value : schedule.getValues()) {
			log.info(" |-> {}", value);
		}
	}
}
