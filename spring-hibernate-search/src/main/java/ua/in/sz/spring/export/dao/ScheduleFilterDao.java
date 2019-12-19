package ua.in.sz.spring.export.dao;

import ua.in.sz.spring.common.FilterDao;
import ua.in.sz.spring.export.entities.ScheduleEntity;

public interface ScheduleFilterDao extends FilterDao {
	void save(ScheduleEntity scheduleEntity);

	ScheduleEntity find(Long id);
}
