package ua.in.sz.hibernate.cache.impl;

import java.util.List;

public interface ScheduleService {
	List<Schedule> loadAll();

	void query();

	void jpqlQuery();

	void save(Schedule schedule);

	void save(List<Schedule> schedules);
}
