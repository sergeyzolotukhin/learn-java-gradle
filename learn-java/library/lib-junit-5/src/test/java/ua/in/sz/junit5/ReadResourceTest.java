package ua.in.sz.junit5;

import com.adelean.inject.resources.junit.jupiter.GivenTextResource;
import com.adelean.inject.resources.junit.jupiter.TestWithResources;
import com.google.common.base.Charsets;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import com.google.common.io.Resources;

import java.net.URL;

@Slf4j
@TestWithResources
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

    @SneakyThrows
    public static String asString(String resource) {
        URL url = Resources.getResource(resource);
        return Resources.toString(url, Charsets.UTF_8);
    }
}