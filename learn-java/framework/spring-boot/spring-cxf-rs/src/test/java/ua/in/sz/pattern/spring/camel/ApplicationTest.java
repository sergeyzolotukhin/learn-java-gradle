package ua.in.sz.pattern.spring.camel;

import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ApplicationTest {

    @Test
    void endpoint() {
        WebRsService client = JAXRSClientFactory.create("http://localhost:8080/ws/api/", WebRsService.class);
        String result = client.sayHi("Serhij Zolotukhin");
        log.info("RESULT: {}", result);
        assertEquals("Hello Serhij Zolotukhin", result);
    }
}