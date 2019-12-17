package ua.in.sz.spring.export;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.in.sz.spring.filter.FilterDao;

import java.util.List;

@Slf4j
@Service
public class ScheduleExportService {
	private FilterDao filterDao;

	public void export() {
		ScheduleExportFilter filter = new ScheduleExportFilter();

		long count = filterDao.count(filter);

		List<ScheduleExportDto> result = filterDao.search(filter);
	}
}
