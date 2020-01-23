package ua.in.szolotukhin.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.util.Collections.singletonList;

@Slf4j
public class Application {

	public static final String BASE_PATH = "lib-jackson/src/main/resources";

	public static void main(String[] args) throws IOException {
		ObjectMapper mapper = new ObjectMapper();

		mapper.writerWithDefaultPrettyPrinter();

		SchedulesFilter filter = SchedulesFilter.builder()
				.scheduleTypes(singletonList("RTBM"))
				.activeVersion(true)
				.build();

		SchedulesRowDataProvider dataProvider = SchedulesRowDataProvider.builder()
				.filter(filter)
				.build();

		String result = mapper.writeValueAsString(dataProvider);

		log.info("{}", result);

		String json = Files.readString(Paths.get(BASE_PATH, "provider.json"));

		SchedulesRowDataProvider provider = mapper.readValue(json, SchedulesRowDataProvider.class);

		log.info("{}", provider);
	}
}
