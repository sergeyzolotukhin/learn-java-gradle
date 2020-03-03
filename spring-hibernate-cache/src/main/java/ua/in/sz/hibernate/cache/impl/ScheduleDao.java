package ua.in.sz.hibernate.cache.impl;

import javax.persistence.EntityManager;

public interface ScheduleDao {
	void save(ScheduleEntity scheduleEntity);

	ScheduleEntity find(Long id);

	EntityManager getEntityManager();
}
