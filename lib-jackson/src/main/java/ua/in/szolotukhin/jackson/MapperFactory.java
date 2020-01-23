package ua.in.szolotukhin.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import ua.in.szolotukhin.jackson.model.RowDataProvider;
import ua.in.szolotukhin.jackson.model.SchedulesRowDataProvider;

public final class MapperFactory {
	public static ObjectMapper createMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.writerWithDefaultPrettyPrinter();

		NamedType[] types = {
				new NamedType(SchedulesRowDataProvider.class, "schedules_intervals"),
				new NamedType(RowDataProvider.class, "all")
		};

		mapper.registerSubtypes(types);
		return mapper;
	}
}
