package ua.in.sz.hibernate.cache.impl;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.stat.SessionStatistics;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {
	private final ScheduleDao scheduleDao;

	public ScheduleServiceImpl(ScheduleDao scheduleDao) {
		this.scheduleDao = scheduleDao;
	}

	@Override
	public void query() {
		Stopwatch stopwatch = Stopwatch.createStarted();

		List<String> names = new ArrayList<>();
		for (int i = 0; i < 100_000; i++) {
			ScheduleEntity scheduleEntity = scheduleDao.find(3L);
			names.add(StringUtils.trim(scheduleEntity.getName()));
		}

		log.info("Schedule count: {}, time {}", CollectionUtils.size(names), stopwatch);

		Session session = (Session)scheduleDao.getEntityManager().getDelegate();
		log.info("Session statistics: {}", session.getStatistics());

		SessionFactory factory = session.getSessionFactory();
//		log.info("Session factory statistics: {}", factory.getStatistics());
		factory.getStatistics().logSummary();
	}
}
