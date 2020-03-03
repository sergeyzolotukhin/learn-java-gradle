package ua.in.sz.hibernate.cache.impl;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {
	private final EntityManager entityManager;

	public ScheduleServiceImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void query() {
		Stopwatch stopwatch = Stopwatch.createStarted();

		List<String> names = new ArrayList<>();
		for (long i = 0; i < 100_000; i++) {
			long id = i / 10_000L + 3L;

			Schedule schedule = entityManager.find(Schedule.class, id);
			names.add(StringUtils.trim(schedule.getName()));

			if (i % 1000 == 0) {
				entityManager.clear();
			}
		}

		log.info("Schedule count: {}, time {}", CollectionUtils.size(names), stopwatch);

		logStats();
	}

	@Override
	public void jpqlQuery() {
		Stopwatch stopwatch = Stopwatch.createStarted();

		List<String> names = new ArrayList<>();
		for (long i = 0; i < 100_000; i++) {
			long id = i / 10_000L + 3L;

			Schedule schedule = entityManager
					.createQuery("select e from Schedule e where e.id = :id", Schedule.class)
					.setParameter("id", id)
					.getSingleResult();

			names.add(StringUtils.trim(schedule.getName()));

			if (i % 1000 == 0) {
				entityManager.clear();
			}
		}

		log.info("Schedule count: {}, time {}", CollectionUtils.size(names), stopwatch);

		logStats();
	}

	public void save(Schedule schedule) {
		entityManager.persist(schedule);
	}

	private void logStats() {
		if (log.isTraceEnabled()) {
			Session session = (Session) entityManager.getDelegate();
			log.trace("Session statistics: {}", session.getStatistics());

			SessionFactory factory = session.getSessionFactory();
			log.trace("Session factory statistics: {}", factory.getStatistics());

			log.trace("Session factory statistics:");
			factory.getStatistics().logSummary();
		}
	}
}
