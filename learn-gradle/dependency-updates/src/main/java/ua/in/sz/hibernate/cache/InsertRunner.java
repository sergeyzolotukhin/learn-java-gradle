package ua.in.sz.hibernate.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ua.in.sz.hibernate.cache.impl.Schedule;
import ua.in.sz.hibernate.cache.impl.ScheduleService;

@Slf4j
@Component
public class InsertRunner implements CommandLineRunner {
	private final ScheduleService scheduleService;

	public InsertRunner(ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}

	@Override
	public void run(String... args) {
		for (int i = 0; i < 10; i++) {
			scheduleService.save(Schedule.builder().name(getClass().getCanonicalName() + " " + i).build());
		}
	}
}
