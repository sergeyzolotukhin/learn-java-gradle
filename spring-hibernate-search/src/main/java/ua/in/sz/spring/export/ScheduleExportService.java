package ua.in.sz.spring.export;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import ua.in.sz.spring.filter.FilterDao;

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
	public void run(String... args) throws Exception {
		ScheduleExportFilter filter = new ScheduleExportFilter();

		long count = filterDao.count(filter);

		Stream<ScheduleExportDto> result = filterDao.search(filter);
	}
}
