package ua.in.szolotukhin.jackson.streaming;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class JacksonStreamingJsonMain {
    public static void main(String[] args) throws Exception {
        writeJson();
        readJson();
    }

    private static void readJson() throws IOException {
        String json = "{\"name\":\"Tom\",\"age\":25,\"address\":[\"Poland\",\"5th avenue\"]}";
        JsonFactory factory = new JsonFactory();
        JsonParser parser = factory.createParser(json);

        String parsedName = null;
        Integer parsedAge = null;
        List<String> addresses = new LinkedList<>();

        while (parser.nextToken() != JsonToken.END_OBJECT) {

            String fieldname = parser.currentName();
            if ("name".equals(fieldname)) {
                parser.nextToken();
                parsedName = parser.getText();

            }

            if ("age".equals(fieldname)) {
                parser.nextToken();
                parsedAge = parser.getIntValue();

            }

            if ("address".equals(fieldname)) {
                parser.nextToken();

                while (parser.nextToken() != JsonToken.END_ARRAY) {
                    addresses.add(parser.getText());
                }
            }
        }
        parser.close();

        log.info("name: {}", parsedName);
        log.info("age: {}", parsedAge);
        log.info("addresses: {}", addresses);
    }

    private static void writeJson() throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        JsonFactory factory = new JsonFactory();
        JsonGenerator generator = factory.createGenerator(stream, JsonEncoding.UTF8);

        generator.writeStartObject();
        generator.writeStringField("name", "Tom");
        generator.writeNumberField("age", 25);
        generator.writeFieldName("address");
        generator.writeStartArray();
        generator.writeString("Poland");
        generator.writeString("5th avenue");
        generator.writeEndArray();
        generator.writeEndObject();
        generator.close();

        String json = stream.toString(StandardCharsets.UTF_8);
        log.info("json: {}", json);
    }
}
