package ua.in.sz.spring.export;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import ua.in.sz.spring.filter.AbstractFilter;

import static org.hibernate.criterion.Projections.projectionList;
import static org.hibernate.criterion.Projections.property;
import static org.hibernate.sql.JoinType.LEFT_OUTER_JOIN;

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
		ProjectionList projection = projectionList();

		projection.add(property("id"), "findingId");
		projection.add(property("title"), "findingTitle");

		projection.add(property("findingRatingAlias.name"), "findingRating");
		projection.add(property("corpDivisionInfrastructureAlias.name"), "division");

		return projection;
	}

	@Override
	protected void aliases(Criteria searchCriteria) {
		searchCriteria.createAlias("audit", "audit", LEFT_OUTER_JOIN);
		searchCriteria.createAlias("findingRating", "findingRatingAlias", LEFT_OUTER_JOIN);
	}

	public Criterion criterion() {
		return and.add(or);
	}
}
