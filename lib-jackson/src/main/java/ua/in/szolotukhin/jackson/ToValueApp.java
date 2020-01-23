package ua.in.szolotukhin.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import ua.in.szolotukhin.jackson.model.SchedulesRowDataProvider;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
public class ToValueApp {

	public static final String BASE_PATH = "lib-jackson/src/main/resources";

	public static void main(String[] args) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.writerWithDefaultPrettyPrinter();

		String json = Files.readString(Paths.get(BASE_PATH, "provider.json"));

		SchedulesRowDataProvider provider = mapper.readValue(json, SchedulesRowDataProvider.class);

		log.info("{}", provider);
	}
}
