package ua.in.sz.hibernate.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ua.in.sz.hibernate.cache.impl.ScheduleService;

@Slf4j
@Component
public class QueryRunner implements CommandLineRunner {
	private final ScheduleService scheduleService;

	public QueryRunner(ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}

	@Override
	public void run(String... args) {
		scheduleService.query();
	}
}
