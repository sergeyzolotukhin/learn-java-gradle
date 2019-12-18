package ua.in.sz.spring.export.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.in.sz.spring.common.AbstractFilterDao;

import javax.persistence.EntityManager;

@Slf4j
@Repository
public class ScheduleFilterDaoImpl extends AbstractFilterDao implements ScheduleFilterDao {

	private final EntityManager entityManager;

	@Autowired
	public ScheduleFilterDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}
}
