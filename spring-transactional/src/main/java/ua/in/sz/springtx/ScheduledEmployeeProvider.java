package ua.in.sz.springtx;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.Assert;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduledEmployeeProvider implements Runnable {

	private final NamedParameterJdbcTemplate template;
	private final PlatformTransactionManager transactionManager;

	@Override
	@SneakyThrows
	@Scheduled(fixedDelay = 500)
	public void run() {
		UUID uuid = UUID.randomUUID();

		try {
			log.info("Creating employer: [{}]", uuid);

			TransactionStatus ts = transactionManager.getTransaction(txDefinition("provide"));

			assertAutoCommitDisabled();

			template.update(
					"insert into EMPLOYEE(EMPLOYEE_ID, EMPLOYEE_NAME) values(:id, :name)",
					Map.of("id", uuid.toString(), "name", "Serhij Zolotukhin"));

			transactionManager.commit(ts);

			log.info("Created employer: [{}]", uuid);
		} catch (DataAccessException e) {
			log.error(e.getMessage(), e);
		}

	}

	private DefaultTransactionDefinition txDefinition(String name) {
		DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
		transactionDefinition.setName(name);
		return transactionDefinition;
	}

	private void assertAutoCommitDisabled() throws SQLException {
		Connection connection = template.getJdbcTemplate().getDataSource().getConnection();
		boolean autoCommit = connection.getAutoCommit();
		connection.close();

		Assert.isTrue(!autoCommit, "Auto commit should is disabled");
	}
}
