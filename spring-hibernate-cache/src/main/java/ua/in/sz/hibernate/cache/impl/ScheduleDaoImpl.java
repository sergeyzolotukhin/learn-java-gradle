package ua.in.sz.hibernate.cache.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Slf4j
@Repository
public class ScheduleDaoImpl implements ScheduleDao {
	private final EntityManager entityManager;

	@Autowired
	public ScheduleDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public void save(ScheduleEntity scheduleEntity) {
		entityManager.persist(scheduleEntity);
	}

	@Override
	public ScheduleEntity find(Long id) {
		return entityManager.getReference(ScheduleEntity.class, id);
	}
}
