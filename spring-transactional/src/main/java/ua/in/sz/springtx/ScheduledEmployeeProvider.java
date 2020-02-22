package ua.in.sz.springtx;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.util.Collections.emptyMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduledEmployeeProvider implements Runnable {

	private final NamedParameterJdbcTemplate template;

	@Override
	@SneakyThrows
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Scheduled(fixedDelay = 500)
	public void run() {
		UUID uuid = UUID.randomUUID();

		try {
			log.info("Creating employer: [{}]", uuid);

			Connection connection = template.getJdbcTemplate().getDataSource().getConnection();
			boolean autoCommit = connection.getAutoCommit();
			connection.close();

			Assert.isTrue(!autoCommit, "Auto commit should is disabled");

			template.update(
					"insert into EMPLOYEE(EMPLOYEE_ID, EMPLOYEE_NAME) values(:id, :name)",
					Map.of("id", uuid.toString(), "name", "Serhij Zolotukhin"));

			log.info("Created employer: [{}]", uuid);
		} catch (DataAccessException e) {
			log.error(e.getMessage(), e);
		}

	}
}
