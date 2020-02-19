package ua.in.sz.springtx;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Collections.emptyMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduledEmployeeIdPrinter implements Runnable {

	private final NamedParameterJdbcTemplate template;

	@Override
	@Transactional(readOnly = true)
	@Scheduled(fixedDelay = 2000)
	public void run() {
		List<String> ids = template.queryForList("select EMPLOYEE_ID from EMPLOYEE", emptyMap(), String.class);

		ids.forEach(id -> log.info("Employer id: [{}]", id));
	}
}
