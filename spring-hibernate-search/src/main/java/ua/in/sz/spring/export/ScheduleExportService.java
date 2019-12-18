package ua.in.sz.spring.export;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class ScheduleExportService implements CommandLineRunner {
	private final FilterDao filterDao;

	@Autowired
	public ScheduleExportService(FilterDao filterDao) {
		this.filterDao = filterDao;
	}

	@Override
	@Transactional
	public void run(String... args) {
		ScheduleExportFilter filter = new ScheduleExportFilter();

		long count = filterDao.count(filter);
		log.info("Count: {}", count);

		Stream<ScheduleExportDto> result = filterDao.search(filter);

		List<ScheduleExportDto> collect = result.collect(Collectors.toList());
		log.info("Result: [{}]", collect.stream().map(ScheduleExportDto::getName).collect(Collectors.joining(", ")));
	}
}
