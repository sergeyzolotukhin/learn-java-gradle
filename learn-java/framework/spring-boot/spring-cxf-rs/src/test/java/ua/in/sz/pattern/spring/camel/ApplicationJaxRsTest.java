package ua.in.sz.pattern.spring.camel;

import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ApplicationJaxRsTest {

    @Test
    void endpoint_2() {
        try (jakarta.ws.rs.client.Client client = ClientBuilder.newClient()) {
            String result = client
                    .register(new Authenticator("admin", "admin"))
                    .target("http://localhost:8080/ws/api/hello")
                    .path("{message}")
                    .resolveTemplate("message", "My Message")
                    .request(MediaType.TEXT_PLAIN)
//                    .header("Authorization", "Basic " + Base64.getEncoder().encodeToString(("admin:admin").getBytes()))
                    .get(String.class);

            log.info("RESULT: {}", result);
        }
    }

    @Provider
    public static class Authenticator implements ClientRequestFilter {
        private final String user;
        private final String password;

        public Authenticator(String user, String password) {
            this.user = user;
            this.password = password;
        }

        @Override
        public void filter(ClientRequestContext requestContext) throws IOException {
            requestContext.getHeaders().add(HttpHeaders.AUTHORIZATION, getBasicAuthentication());
        }

        private String getBasicAuthentication() {
            String userAndPassword = this.user + ":" + this.password;
            byte[] userAndPasswordBytes = userAndPassword.getBytes(StandardCharsets.UTF_8);
            return "Basic " + Base64.getEncoder().encodeToString(userAndPasswordBytes);
        }
    }
}