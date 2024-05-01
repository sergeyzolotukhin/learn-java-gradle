package ua.in.sz.junit5;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import ua.in.sz.spring.HelloWorldController;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
class MainTest {
    @Test
    void main_1() throws Exception {
        URI uri = HelloWorldController.class.getProtectionDomain().getCodeSource().getLocation().toURI();
        log.info("jar URI: [{}]", uri);
    }
}