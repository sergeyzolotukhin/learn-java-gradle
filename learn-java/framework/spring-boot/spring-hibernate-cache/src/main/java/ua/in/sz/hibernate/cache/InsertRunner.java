package ua.in.sz.hibernate.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ua.in.sz.hibernate.cache.impl.Schedule;
import ua.in.sz.hibernate.cache.impl.ScheduleService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@Order(0)
public class InsertRunner implements CommandLineRunner {
	private final ScheduleService scheduleService;

	public InsertRunner(ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}

	@Override
	public void run(String... args) {
		List<Schedule> schedules = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			schedules.add(Schedule.builder().name(getClass().getCanonicalName() + " " + i).build());
		}
		scheduleService.save(schedules);
	}
}
