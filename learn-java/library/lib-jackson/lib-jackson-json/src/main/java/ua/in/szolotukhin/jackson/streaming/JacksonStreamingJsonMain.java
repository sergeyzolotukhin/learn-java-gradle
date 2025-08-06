package ua.in.szolotukhin.jackson.streaming;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
public class JacksonStreamingJsonMain {
    public static void main(String[] args) throws Exception {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        JsonFactory factory = new JsonFactory();
        JsonGenerator generator = factory.createGenerator(stream, JsonEncoding.UTF8);

        // when
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

        // then
        String json = stream.toString(StandardCharsets.UTF_8);
        log.info("json: {}", json);
    }
}
