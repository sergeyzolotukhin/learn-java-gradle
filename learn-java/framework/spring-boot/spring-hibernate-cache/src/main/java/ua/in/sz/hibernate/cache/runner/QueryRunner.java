package ua.in.sz.hibernate.cache.runner;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ua.in.sz.hibernate.cache.impl.ScheduleService;

@Slf4j
@Component
@Order(1)
public class QueryRunner implements CommandLineRunner {
	private final ScheduleService scheduleService;

	public QueryRunner(ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}

	@Override
	@SneakyThrows
	public void run(String... args) {
		log.info("QueryRunner first start");
		scheduleService.criteriaQuery();

//		TimeUnit.SECONDS.sleep(0);

//		log.info("QueryRunner second start");
//		scheduleService.query();
	}
}
