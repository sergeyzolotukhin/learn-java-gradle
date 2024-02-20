package ua.in.sz.pattern.spring.camel;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration({"classpath:/test-conduit-application-context.xml"})
class ApplicationXmlWithConduitTest implements ApplicationContextAware {

    @Autowired
    private WebService testClient;
    @Autowired
    private SecondWebService testSecondClient;
    @Setter
    private ApplicationContext applicationContext;

    @Test
    void endpoint() {
        login("testClient", "admin", "admin");
        String result = testClient.sayHi("General Kenobi");
        log.info("soap response: [{}]", result);
        assertEquals("Hello General Kenobi admin", result);

        login("testClient", "user", "user");
        String result2 = testClient.sayHi("General Kenobi");
        log.info("soap response: [{}]", result2);
        assertEquals("Hello General Kenobi user", result2);

        login("testSecondClient", "admin", "admin");
        login("testSecondClient", "user", "user");
        String result3 = testSecondClient.sayHi("General Kenobi");
        log.info("soap response: [{}]", result3);
        assertEquals("Hello second: General Kenobi user", result3);
    }

    private void login(String serviceName, String user, String password) {
        Object client = applicationContext.getBean(serviceName);
        AuthorizationPolicy policy = policy(user, password);
        setAuth(client, policy);
    }

    private static void setAuth(Object service, AuthorizationPolicy authorizationPolicy) {
        Client client = ClientProxy.getClient(service);
        HTTPConduit conduit = (HTTPConduit) client.getConduit();
        conduit.setAuthorization(authorizationPolicy);

        log.info("cxf client with url: {} has user : {}",
                client.getEndpoint().getEndpointInfo().getAddress(),
                ((HTTPConduit) client.getConduit()).getAuthorization().getUserName());
    }

    private static AuthorizationPolicy policy(String user, String password) {
        AuthorizationPolicy authorizationPolicy2 = new AuthorizationPolicy();
        authorizationPolicy2.setUserName(user);
        authorizationPolicy2.setPassword(password);
        authorizationPolicy2.setAuthorizationType("Basic");
        return authorizationPolicy2;
    }
}