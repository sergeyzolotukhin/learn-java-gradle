package ua.in.sz.hibernate.cache.impl;

import com.google.common.base.Stopwatch;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jpa.QueryHints;
import org.springframework.stereotype.Service;

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
	public List<Schedule> loadAll() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		CriteriaQuery<Schedule> q = cb.createQuery(Schedule.class);
		q.select(q.from(Schedule.class));
		TypedQuery<Schedule> query = entityManager.createQuery(q);

		return query.getResultList();
	}

	@Override
	public void query() {
		Stopwatch stopwatch = Stopwatch.createStarted();

		loadAll();

		List<String> names = new ArrayList<>();
		for (int j = 0; j < 10_000; j++) {
			for (long id = 1; id < 10; id++) {
				Schedule schedule = entityManager.find(Schedule.class, id);
				names.add(StringUtils.trim(schedule.getName()));
			}
			entityManager.clear();
		}

		log.info("Schedule count: {}, time {}", CollectionUtils.size(names), stopwatch);

		logStats();
	}

	@Override
	public void criteriaQuery() {
		Stopwatch stopwatch = Stopwatch.createStarted();

		loadAll();

		List<String> names = new ArrayList<>();
		for (int j = 0; j < 10; j++) {
			for (long id = 1; id < 10; id++) {

				CriteriaBuilder cb = entityManager.getCriteriaBuilder();

				CriteriaQuery<Schedule> q = cb.createQuery(Schedule.class);
				Root<Schedule> from = q.from(Schedule.class);
				q.select(from);
				Predicate equalId = cb.equal(from.get("id"), id);
				q.where(equalId);

				TypedQuery<Schedule> query = entityManager.createQuery(q);
//				query.setHint(QueryHints.HINT_CACHEABLE, Boolean.FALSE);
//				query.setHint(QueryHints.HINT_CACHE_MODE, "IGNORE");

				Schedule schedule = entityManager.find(Schedule.class, id);
				names.add(StringUtils.trim(schedule.getName()));
			}
			entityManager.clear();
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

	@Override
	public void save(List<Schedule> schedules) {
		schedules.forEach(this::save);

//		logStats();
	}

	public void save(Schedule schedule) {
		entityManager.persist(schedule);
	}

	private void logStats() {
		Session session = (Session) entityManager.getDelegate();
		SessionFactory factory = session.getSessionFactory();

		log.info("Session statistics: {}", session.getStatistics());
		if (log.isTraceEnabled()) {
			log.trace("Session factory statistics: {}", factory.getStatistics());

			log.trace("Session factory statistics:");
		}

		factory.getStatistics().logSummary();

		log.info("Sessions opened: {}", factory.getStatistics().getSessionOpenCount());
		log.info("Sessions closed: {}", factory.getStatistics().getSessionCloseCount());
		log.info("Entities loaded: {}", factory.getStatistics().getEntityLoadCount());

		log.info("Second level cache puts: {}", factory.getStatistics().getSecondLevelCachePutCount());
		log.info("Second level cache hits: {}", factory.getStatistics().getSecondLevelCacheHitCount());
		log.info("Second level cache misses: {}", factory.getStatistics().getSecondLevelCacheMissCount());
		log.info("Entities loaded: {}", factory.getStatistics().getEntityLoadCount());
	}
}
