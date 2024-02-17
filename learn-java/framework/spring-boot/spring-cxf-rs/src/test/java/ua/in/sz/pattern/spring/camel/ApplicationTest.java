package ua.in.sz.pattern.spring.camel;

import lombok.extern.slf4j.Slf4j;
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
        WebRsService client = JAXRSClientFactory.create("http://localhost:8080/ws/api/", WebRsService.class);

//        BindingProvider provider = (BindingProvider) client;
//        Map<String,Object> rc = provider.getRequestContext();
//        rc.put(BindingProvider.USERNAME_PROPERTY, userName);
//        rc.put(BindingProvider.PASSWORD_PROPERTY, password);

        WebClient.client(client).header("Authorization", "Basic " +  Base64.getEncoder().encodeToString(("admin:admin").getBytes()));

        String result = client.sayHi("Serhij Zolotukhin");
        log.info("RESULT: {}", result);
        assertEquals("Hello Serhij Zolotukhin", result);
    }
}