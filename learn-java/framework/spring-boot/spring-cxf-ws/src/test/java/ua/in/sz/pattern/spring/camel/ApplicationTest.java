package ua.in.sz.pattern.spring.camel;

import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.junit.jupiter.api.AfterEach;
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
//        log.info("Started {}", this);

        JaxWsProxyFactoryBean jaxWsProxyFactory = new JaxWsProxyFactoryBean();
        jaxWsProxyFactory.setServiceClass(WebService.class);
        jaxWsProxyFactory.setServiceName(new QName(
                "http://camel.spring.pattern.sz.in.ua/",
                "WebServiceImplService"
        ));
        jaxWsProxyFactory.setAddress("http://localhost:8080/ws/Hello");
//        jaxWsProxyFactory.getInInterceptors().add(new LoggingInInterceptor());
//        jaxWsProxyFactory.getOutInterceptors().add(new LoggingOutInterceptor());
        testClient = jaxWsProxyFactory.create(WebService.class);

        AuthorizationPolicy authorizationPolicy = new AuthorizationPolicy();
        authorizationPolicy.setUserName("admin");
        authorizationPolicy.setPassword("admin");
        authorizationPolicy.setAuthorizationType("Basic");

        Client client = ClientProxy.getClient(testClient);
        HTTPConduit conduit = (HTTPConduit)client.getConduit();
        conduit.setAuthorization(authorizationPolicy);
    }

    @AfterEach
    void tearDown() {
//        log.info("Ended {}", this);
    }

    @Test
    void endpoint() {
        String result = testClient.sayHi("General Kenobi");
        log.info("soap response: [{}]", result);
        assertEquals("Hello General Kenobi admin", result);

        AuthorizationPolicy authorizationPolicy = new AuthorizationPolicy();
        authorizationPolicy.setUserName("user");
        authorizationPolicy.setPassword("user");
        authorizationPolicy.setAuthorizationType("Basic");

        Client client = ClientProxy.getClient(testClient);
        HTTPConduit conduit = (HTTPConduit)client.getConduit();
        conduit.setAuthorization(authorizationPolicy);

        String result2 = testClient.sayHi("General Kenobi");
        log.info("soap response: [{}]", result2);
        assertEquals("Hello General Kenobi user", result2);

        log.info("Ended");
    }
}