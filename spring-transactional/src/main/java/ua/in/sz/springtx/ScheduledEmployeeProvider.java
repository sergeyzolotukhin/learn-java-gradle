package ua.in.sz.springtx;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.util.Collections.emptyMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduledEmployeeProvider {

	private final NamedParameterJdbcTemplate template;

	@Scheduled(fixedDelay = 500)
	public void run() {
		UUID uuid = UUID.randomUUID();

		try {
			log.info("Creating employer: [{}]", uuid);

			template.update(
					"insert into EMPLOYEE(EMPLOYEE_ID, EMPLOYEE_NAME) values(:id, :name)",
					Map.of("id", uuid.toString(), "name", "Serhij Zolotukhin"));

			log.info("Created employer: [{}]", uuid);
		} catch (DataAccessException e) {
			log.error(e.getMessage(), e);
		}

	}
}
