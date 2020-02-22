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
import org.springframework.util.Assert;

import java.sql.Connection;
import java.sql.SQLException;

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

			Connection connection = template.getDataSource().getConnection();
			boolean autoCommit = connection.getAutoCommit();
			connection.close();

			Assert.isTrue(!autoCommit, "Auto commit should is disabled");

			template.update("delete from employee");

			log.info("Cleaned employers");
		} catch (DataAccessException | SQLException e) {
			log.error(e.getMessage(), e);
		}
	}
}
