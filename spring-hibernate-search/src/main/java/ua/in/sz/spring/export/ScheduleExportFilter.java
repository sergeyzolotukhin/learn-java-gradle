package ua.in.sz.spring.export;

import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

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
	protected Selection<?>[] selection(Root<ScheduleEntity> from) {
		return new Selection<?>[]{
				from.get(ScheduleEntity.Fields.name)
		};
	}
}
