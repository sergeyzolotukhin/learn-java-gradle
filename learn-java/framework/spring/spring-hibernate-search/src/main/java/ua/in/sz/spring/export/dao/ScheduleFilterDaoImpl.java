package ua.in.sz.spring.export.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.in.sz.spring.common.AbstractFilterDao;
import ua.in.sz.spring.export.entities.ScheduleEntity;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static javax.transaction.Transactional.TxType.REQUIRES_NEW;

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

	@Override
	@Transactional(REQUIRES_NEW)
	public void save(ScheduleEntity scheduleEntity) {
		entityManager.persist(scheduleEntity);
	}

	@Override
	@Transactional(REQUIRES_NEW)
	public ScheduleEntity find(Long id) {
		return entityManager.find(ScheduleEntity.class, id);
	}
}
