package ua.in.sz.pattern.spring.camel;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration({"classpath:/test-auth-supplier-application-context.xml"})
class ApplicationXmlAuthSupplierTest {

    @Autowired
    private WebService testClient;

    @Test
    void endpoint() {
        String result = testClient.sayHi("General Kenobi");

        log.info("soap response: [{}]", result);
        assertEquals("Hello General Kenobi", result);
    }
}