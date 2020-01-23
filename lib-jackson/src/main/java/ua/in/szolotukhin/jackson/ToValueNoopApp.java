package ua.in.szolotukhin.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import ua.in.szolotukhin.jackson.json.MapperFactory;
import ua.in.szolotukhin.jackson.model.RowDataProvider;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
public class ToValueNoopApp {

	public static final String BASE_PATH = "lib-jackson/src/main/resources";

	public static void main(String[] args) throws IOException {
		ObjectMapper mapper = MapperFactory.createMapper();

		String json = Files.readString(Paths.get(BASE_PATH, "provider-noop.json"));

		RowDataProvider provider = mapper.readValue(json, RowDataProvider.class);

		log.info("{}", provider);
	}
}
