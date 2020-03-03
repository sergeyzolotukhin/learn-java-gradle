package ua.in.sz.hibernate.cache.impl;

import javax.persistence.EntityManager;

public interface ScheduleDao {
	EntityManager getEntityManager();

	void save(ScheduleEntity scheduleEntity);

	ScheduleEntity find(Long id);

	ScheduleEntity find(String jpql, Long id);
}
