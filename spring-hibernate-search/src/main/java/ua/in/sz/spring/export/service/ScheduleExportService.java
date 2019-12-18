package ua.in.sz.spring.export.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import ua.in.sz.spring.export.dao.ScheduleFilterDao;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class ScheduleExportService implements CommandLineRunner {
	private final ScheduleFilterDao scheduleFilterDao;

	@Autowired
	public ScheduleExportService(ScheduleFilterDao scheduleFilterDao) {
		this.scheduleFilterDao = scheduleFilterDao;
	}

	@Override
	@Transactional
	public void run(String... args) {
		ScheduleExportFilter filter = new ScheduleExportFilter();

		long count = scheduleFilterDao.count(filter);
		log.info("Count: {}", count);

		Stream<ScheduleExportDto> result = scheduleFilterDao.search(filter);

		List<ScheduleExportDto> collect = result.collect(Collectors.toList());
		log.info("Result: [{}]", collect.stream().map(ScheduleExportDto::getName).collect(Collectors.joining(", ")));
	}
}
