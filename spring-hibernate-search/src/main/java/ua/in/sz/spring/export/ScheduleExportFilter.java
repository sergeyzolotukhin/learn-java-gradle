package ua.in.sz.spring.export;

import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.Collections;
import java.util.List;

public class ScheduleExportFilter extends AbstractFilter<ScheduleEntity, ScheduleExportDto> {

	@Override
	public Class<ScheduleEntity> entityClass() {
		return ScheduleEntity.class;
	}

	@Override
	public Class<ScheduleExportDto> dtoClass() {
		return ScheduleExportDto.class;
	}

	protected List<Selection<?>> selection(Root<ScheduleEntity> from) {
		return Collections.singletonList(
				from.get(ScheduleEntity.Fields.name)
		);
	}
}
