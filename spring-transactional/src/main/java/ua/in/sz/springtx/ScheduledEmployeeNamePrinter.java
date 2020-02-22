package ua.in.sz.springtx;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.sql.Connection;
import java.util.List;

import static java.util.Collections.emptyMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduledEmployeeNamePrinter implements Runnable {

	private final NamedParameterJdbcTemplate template;

	@Override
	@SneakyThrows
	@Transactional(readOnly = true)
	@Scheduled(fixedDelay = 1000)
	public void run() {
		Connection connection = template.getJdbcTemplate().getDataSource().getConnection();
		boolean autoCommit = connection.getAutoCommit();
		connection.close();

		Assert.isTrue(!autoCommit, "Auto commit should is disabled");

		List<String> names = template.queryForList("select EMPLOYEE_NAME from EMPLOYEE", emptyMap(), String.class);

		names.forEach(name -> log.info("Employer name: [{}]", name));
	}
}
