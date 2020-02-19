package ua.in.sz.springtx;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Collections.emptyMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApplicationRunner implements CommandLineRunner {

	private final NamedParameterJdbcTemplate template;

	@Override
	public void run(String... args) {
		List<String> names = template.queryForList("select EMPLOYEE_NAME from EMPLOYEE", emptyMap(), String.class);

		names.forEach(name -> log.info("Employer name: [{}]", name));
	}
}
