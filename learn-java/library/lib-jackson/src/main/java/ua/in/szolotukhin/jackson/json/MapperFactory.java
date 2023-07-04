package ua.in.szolotukhin.jackson.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import ua.in.szolotukhin.jackson.model.NoopRowDataProvider;
import ua.in.szolotukhin.jackson.model.SchedulesFilter;
import ua.in.szolotukhin.jackson.model.SchedulesRowDataProvider;

public final class MapperFactory {
	public static ObjectMapper createMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.writerWithDefaultPrettyPrinter();
		mapper.registerSubtypes(namedTypes());
		mapper.addMixIn(SchedulesFilter.class, SchedulesFilterMixIn.class);
		return mapper;
	}

	private static NamedType[] namedTypes() {
		return new NamedType[]{
				new NamedType(SchedulesRowDataProvider.class, "schedules_intervals"),
				new NamedType(NoopRowDataProvider.class, "noop")
		};
	}
}
