package ua.in.sz.hibernate.cache;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ua.in.sz.hibernate.cache.impl.ScheduleService;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class QueryRunner implements CommandLineRunner {
	private final ScheduleService scheduleService;

	public QueryRunner(ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}

	@Override
	@SneakyThrows
	public void run(String... args) {
		scheduleService.query();

		TimeUnit.SECONDS.sleep(30);

		scheduleService.query();
	}
}
