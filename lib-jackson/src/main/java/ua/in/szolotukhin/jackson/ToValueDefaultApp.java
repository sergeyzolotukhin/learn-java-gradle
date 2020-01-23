package ua.in.szolotukhin.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import ua.in.szolotukhin.jackson.json.MapperFactory;
import ua.in.szolotukhin.jackson.model.AbstractRowDataProvider;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
public class ToValueDefaultApp {

	public static final String BASE_PATH = "lib-jackson/src/main/resources";
	public static final String DEFAULT = "provider-default-version.json";

	public static void main(String[] args) throws IOException {
		ObjectMapper mapper = MapperFactory.createMapper();

		String json = Files.readString(Paths.get(BASE_PATH, DEFAULT));

		AbstractRowDataProvider provider = mapper.readValue(json, AbstractRowDataProvider.class);

		log.info("{}", provider);
	}
}
