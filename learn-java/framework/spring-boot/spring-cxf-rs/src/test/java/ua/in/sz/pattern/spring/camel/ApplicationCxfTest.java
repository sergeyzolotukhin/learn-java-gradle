package ua.in.sz.pattern.spring.camel;

import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.jaxrs.client.Client;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ApplicationCxfTest {

    @Test
    void endpoint() {
        WebRsService proxy = JAXRSClientFactory.create("http://localhost:8080/ws/api/", WebRsService.class);
        Client client = WebClient.client(proxy);
        client.authorization("Basic " +  Base64.getEncoder().encodeToString(("admin:admin").getBytes()));

        String result = proxy.sayHi("Serhij Zolotukhin");
        log.info("RESULT: {}", result);
        assertEquals("Hello Serhij Zolotukhin", result);
    }
}