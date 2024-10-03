package ua.in.szolotukhin.jackson;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import lombok.extern.slf4j.Slf4j;
import ua.in.szolotukhin.jackson.json.MapperFactory;
import ua.in.szolotukhin.jackson.model.AbstractRowDataProvider;
import ua.in.szolotukhin.jackson.model.SchedulesRowDataProvider;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

@Slf4j
public class ToValueDefaultApp {

	public static final String BASE_PATH = "learn-java/library/lib-jackson/src/main/resources";
	public static final String DEFAULT = "provider-default-version.json";

	public static void main(String[] args) throws IOException {
		log.info("Working directory: {}", System.getProperty("user.dir"));
		ObjectMapper mapper = MapperFactory.createMapper()
				.addHandler(new ProblemHandler())
				;

		for (Module module : ObjectMapper.findModules()){
			log.info("Module: {}", module);
		}

		String json = Files.readString(Paths.get(BASE_PATH, DEFAULT));

		SchedulesRowDataProvider provider = (SchedulesRowDataProvider) mapper.readValue(json, AbstractRowDataProvider.class);

		Objects.requireNonNull(provider.getFilter().getActiveVersion(), "Active version should be not null");
		log.info("{}", provider);
	}

	private static class ProblemHandler extends DeserializationProblemHandler {
		public Object handleWeirdStringValue(DeserializationContext ctxt,
											 Class<?> targetType, String valueToConvert,
											 String failureMsg)
				throws IOException
		{
			log.error("Weird string value to convert: {}", valueToConvert);
			return null;
		}
	}
}
