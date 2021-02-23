package ua.in.sz.springtx;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.Assert;

import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
//@Component
@RequiredArgsConstructor
public class ScheduledEmployeeCleaner implements CommandLineRunner, Runnable {

	private final JdbcTemplate template;
	private final PlatformTransactionManager transactionManager;


	@Override
	public void run(String... args) {
		run();
	}

	@Override
	@Scheduled(initialDelay = 5000, fixedDelay = 5000)
	public void run() {
		try {
			log.info("Cleaning employers");

			TransactionStatus tx = transactionManager.getTransaction(txDefinition("clear"));

			assertAutoCommitDisabled();

			template.update("delete from employee");

			transactionManager.commit(tx);

			log.info("Cleaned employers");
		} catch (DataAccessException | SQLException e) {
			log.error(e.getMessage(), e);
		}
	}

	private DefaultTransactionDefinition txDefinition(String name) {
		DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
		transactionDefinition.setName(name);
		return transactionDefinition;
	}

	private void assertAutoCommitDisabled() throws SQLException {
		Connection connection = template.getDataSource().getConnection();
		boolean autoCommit = connection.getAutoCommit();
		connection.close();

		Assert.isTrue(!autoCommit, "Auto commit should is disabled");
	}
}
