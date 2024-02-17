package ua.in.sz.pattern.spring.camel;

import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.namespace.QName;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ApplicationTest {

    private WebService testClient;

    @BeforeEach
    void setUp() {
        JaxWsProxyFactoryBean jaxWsProxyFactory = new JaxWsProxyFactoryBean();
        jaxWsProxyFactory.setServiceClass(WebService.class);
        jaxWsProxyFactory.setServiceName(new QName(
                "http://camel.spring.pattern.sz.in.ua/",
                "WebServiceImplService"
        ));
        jaxWsProxyFactory.setAddress("http://localhost:8080/ws/Hello");
        testClient = jaxWsProxyFactory.create(WebService.class);
    }

    @Test
    void endpoint() {
        String result = testClient.sayHi("General Kenobi");
        log.info("soap response: [{}]", result);
        assertEquals("Hello General Kenobi", result);
    }
}