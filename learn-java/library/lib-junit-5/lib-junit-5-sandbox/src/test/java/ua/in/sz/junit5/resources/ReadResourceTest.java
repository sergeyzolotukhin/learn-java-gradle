package ua.in.sz.junit5.resources;

import com.adelean.inject.resources.junit.jupiter.GivenTextResource;
import com.adelean.inject.resources.junit.jupiter.TestWithResources;
import com.google.common.base.Charsets;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import com.google.common.io.Resources;
import org.junit.jupiter.api.extension.ExtendWith;
import ua.in.sz.junit5.resources.extension.ResourceFile;
import ua.in.sz.junit5.resources.extension.ResourceParameterResolver;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Slf4j
@TestWithResources
@ExtendWith(ResourceParameterResolver.class)
class ReadResourceTest {

    @GivenTextResource("resource.txt")
    String instanceField;

    @Test
    void weCanAccessToFile() {
        log.info("TEXT: {}", instanceField);
    }

    @Test
    void weCanAccessToFileViaGuava() {
        String actual = asString("resource-2.txt");
        log.info("TEXT: {}", actual);
    }

    @Test
    @SneakyThrows
    void weCanAccessToFilePlainJava() {
        try (InputStream inputStream = Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("resource-2.txt"))){
            String text = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            log.info("TEXT: {}", text);
        }
    }

    @Test
    public void testIt(@ResourceFile("resource-3.txt") String text) {
        log.info("TEXT: {}", text);
    }

    @SneakyThrows
    public static String asString(String resource) {
        return Resources.toString(Resources.getResource(resource), Charsets.UTF_8);
    }
}