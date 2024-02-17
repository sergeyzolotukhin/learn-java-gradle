package ua.in.sz.pattern.spring.camel;

import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.xml.namespace.QName;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration({"classpath:/test-application-context.xml"})
class ApplicationXmlTest {

    @Autowired
    private WebService testClient;
    @Autowired
    private SecondWebService testSecondClient;

    @Test
    void endpoint() {
        String result = testClient.sayHi("General Kenobi");
        log.info("soap response: [{}]", result);
        assertEquals("Hello General Kenobi", result);

        String result2 = testSecondClient.sayHi("General Kenobi");
        log.info("soap response: [{}]", result2);
        assertEquals("Hello second: General Kenobi", result2);
    }
}