package ua.in.sz.spring.export.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import ua.in.sz.spring.export.dao.ScheduleFilterDao;
import ua.in.sz.spring.export.entities.ScheduleEntity;

import javax.transaction.Transactional;
import java.util.ArrayList;
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

		for (int i = 0; i < 100_000; i++) {
			scheduleFilterDao.save(ScheduleEntity.builder().name(getClass().getCanonicalName() + " " + i).build());
		}

		long count = scheduleFilterDao.count(filter);
		log.info("Count: {}", count);

		Stream<ScheduleExportDto> result = scheduleFilterDao.search(filter);

		log.info("Result: [{}]", toNames(result));

		List<String> names = new ArrayList<>();
		for (int i = 0; i < 100_000; i++) {
			ScheduleEntity scheduleEntity = scheduleFilterDao.find(1L);
			names.add(scheduleEntity.getName());
		}

		log.info("Result 2: [{}]", names.stream().limit(10).collect(Collectors.joining(", ")));
		log.info("end");
	}

	private String toNames(Stream<ScheduleExportDto> result) {
		return result.map(ScheduleExportDto::getName).limit(10).collect(Collectors.joining(", "));
	}
}
