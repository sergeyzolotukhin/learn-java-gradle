package ua.in.szolotukhin.jackson;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class JSR374App {

	public static void main(String[] args) throws IOException {
		JsonObject build = Json.createObjectBuilder()
				.add("name", "Name")
				.add("detail", Json.createObjectBuilder()
						.add("second", "value")
						.build()
				)
				.build();

		log.info("{}", build);
	}
}
