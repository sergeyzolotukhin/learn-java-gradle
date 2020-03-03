package ua.in.sz.hibernate.cache.impl;

public interface ScheduleDao {
	void save(ScheduleEntity scheduleEntity);

	ScheduleEntity find(Long id);
}
