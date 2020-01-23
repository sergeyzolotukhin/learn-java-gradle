package ua.in.szolotukhin.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import ua.in.szolotukhin.jackson.json.MapperFactory;
import ua.in.szolotukhin.jackson.model.AbstractRowDataProvider;
import ua.in.szolotukhin.jackson.model.SchedulesRowDataProvider;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

@Slf4j
public class ToValueIntervalsApp {

	public static final String BASE_PATH = "lib-jackson/src/main/resources";
	public static final String DEFAULT = "provider-intervals.json";

	public static void main(String[] args) throws IOException {
		ObjectMapper mapper = MapperFactory.createMapper();

		String json = Files.readString(Paths.get(BASE_PATH, DEFAULT));

		SchedulesRowDataProvider provider = (SchedulesRowDataProvider) mapper.readValue(json, AbstractRowDataProvider.class);

		Objects.requireNonNull(provider.getFilter().getActiveVersion(), "Active version should be not null");
		log.info("{}", provider);
	}
}
