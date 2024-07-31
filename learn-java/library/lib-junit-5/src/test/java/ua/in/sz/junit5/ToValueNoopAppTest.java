package ua.in.sz.junit5;

import com.adelean.inject.resources.junit.jupiter.GivenTextResource;
import com.adelean.inject.resources.junit.jupiter.TestWithResources;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
@TestWithResources
class ToValueNoopAppTest {

    @GivenTextResource("resource.txt")
    String instanceField;

    @Test
    void weCanAccessToFile() {
        log.info("TEXT: {}", instanceField);
    }
}