package ua.in.sz.pattern.spring.camel;

import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.jaxrs.client.Client;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ApplicationTest {

    @Test
    void endpoint() {
        WebRsService proxy = JAXRSClientFactory.create("http://localhost:8080/ws/api/", WebRsService.class);
        Client client = WebClient.client(proxy);
        client.authorization("Basic " +  Base64.getEncoder().encodeToString(("admin:admin").getBytes()));

        String result = proxy.sayHi("Serhij Zolotukhin");
        log.info("RESULT: {}", result);
        assertEquals("Hello Serhij Zolotukhin", result);
    }

    @Test
    void endpoint_2() {
        String result = ClientBuilder.newClient()
                .target("http://localhost:8080/ws/api/hello")
                .path("{message}")
                .resolveTemplate("message", "My Message")
                .request(MediaType.TEXT_PLAIN)
                .header("Authorization", "Basic " +  Base64.getEncoder().encodeToString(("admin:admin").getBytes()))
                .get(String.class);

        log.info("RESULT: {}", result);
    }
}