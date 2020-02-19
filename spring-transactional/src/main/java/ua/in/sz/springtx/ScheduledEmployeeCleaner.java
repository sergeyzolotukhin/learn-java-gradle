package ua.in.sz.springtx;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduledEmployeeCleaner implements CommandLineRunner, Runnable {

	private final JdbcTemplate template;


	@Override
	public void run(String... args) {
		run();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Scheduled(initialDelay = 5000, fixedDelay = 5000)
	public void run() {
		try {
			log.info("Cleaning employers");

			template.update("delete from employee");

			log.info("Cleaned employers");
		} catch (DataAccessException e) {
			log.error(e.getMessage(), e);
		}
	}
}
