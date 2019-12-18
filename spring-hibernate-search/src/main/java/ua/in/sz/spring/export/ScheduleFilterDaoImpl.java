package ua.in.sz.spring.export;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.in.sz.spring.common.AbstractFilterDao;

import javax.persistence.EntityManager;

@Slf4j
@Repository
public class ScheduleFilterDaoImpl extends AbstractFilterDao {
	@Autowired
	public ScheduleFilterDaoImpl(EntityManager entityManager) {
		super(entityManager);
	}
}
