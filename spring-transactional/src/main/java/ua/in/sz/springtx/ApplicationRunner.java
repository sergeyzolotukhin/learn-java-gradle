package ua.in.sz.springtx;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApplicationRunner implements CommandLineRunner {

	private final NamedParameterJdbcTemplate template;

	@Override
	public void run(String... args) throws Exception {
		log.info("Running ...");
	}
}
