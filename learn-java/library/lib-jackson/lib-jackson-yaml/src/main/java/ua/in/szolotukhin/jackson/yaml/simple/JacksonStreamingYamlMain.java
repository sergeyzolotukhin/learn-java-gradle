package ua.in.szolotukhin.jackson.yaml.simple;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class JacksonStreamingYamlMain {
    public static void main(String[] args) throws Exception {
        writeYaml();
    }

    private static void writeYaml() throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        YAMLFactory factory = new YAMLFactory();
        YAMLGenerator generator = factory.createGenerator(stream, JsonEncoding.UTF8);

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

        String yaml = stream.toString(StandardCharsets.UTF_8);
        log.info("yaml: \n===\n{}\n===\n", yaml);
    }
}
