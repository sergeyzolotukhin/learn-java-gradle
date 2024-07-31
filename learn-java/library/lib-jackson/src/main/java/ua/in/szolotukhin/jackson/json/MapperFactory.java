package ua.in.szolotukhin.jackson.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ua.in.szolotukhin.jackson.model.NoopRowDataProvider;
import ua.in.szolotukhin.jackson.model.SchedulesFilter;
import ua.in.szolotukhin.jackson.model.SchedulesRowDataProvider;

// https://stackoverflow.com/questions/21384820/is-there-a-jackson-datatype-module-for-jdk8-java-time
public final class MapperFactory {
	public static ObjectMapper createMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.writerWithDefaultPrettyPrinter();
		mapper.registerSubtypes(namedTypes());
		mapper.registerModule(new JavaTimeModule());
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
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
