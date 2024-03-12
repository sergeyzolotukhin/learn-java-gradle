package ua.in.szolotukhin.jackson;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import java.io.IOException;

@Slf4j
public class ToJsonApp {

	public static void main(String[] args) throws IOException {
		JSONObject jo = new JSONObject()
				.put("abc", "def")
				.put("cde", new JSONObject()
						.put("ddd", "vvv"));

		log.info("{}", jo.toString(1));

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
