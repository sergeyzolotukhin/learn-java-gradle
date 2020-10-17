package ua.in.sz.junit;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

@Slf4j
class JsonTest {
	@Test
	void jsonAssert() throws JSONException {
		String actual = "{id:123, name:\"John\"}";

		String expected = "{id:123,name:\"John\"}";
		JSONAssert.assertEquals(expected, actual, JSONCompareMode.LENIENT);
	}

	@Test
	void jsonMapperAssert() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();

		String actual = "{ \"color\" : \"Black\", \"name\" : \"FIAT\" }";
		String expected = "{ \"color\" : \"Black\", \"name\" : \"FIAT\" }";

		JsonNode jsonNode = objectMapper.readTree(actual);
		JsonNode jsonNode2 = objectMapper.readTree(expected);

		JSONAssert.assertEquals(jsonNode.toString(), jsonNode2.toString(), JSONCompareMode.LENIENT);
	}
}
