package ua.in.sz.spring.export;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import static org.hibernate.criterion.Projections.projectionList;

public class ScheduleExportFilter extends AbstractFilter<ScheduleEntity, ScheduleExportDto> {

	@Override
	public Class<ScheduleEntity> entityClass() {
		return ScheduleEntity.class;
	}

	@Override
	public Class<ScheduleExportDto> dtoClass() {
		return ScheduleExportDto.class;
	}

	@Override
	protected Projection projection() {
		return projectionList();
	}

	@Override
	protected void aliases(Criteria searchCriteria) {

	}

	public Criterion criterion() {
		return and.add(or);
	}

	@Override
	public CriteriaQuery<ScheduleExportDto> searchQuery(CriteriaBuilder cb) {
			CriteriaQuery<ScheduleExportDto> cq = cb.createQuery(dtoClass());

			Root<ScheduleEntity> from = cq.from(entityClass());
			cq.select(from.get("name"));

			return cq;
	}
}
