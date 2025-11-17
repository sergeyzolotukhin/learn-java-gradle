package ua.in.sz.spring.mvc.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AcceptanceTest implements ApplicationContextAware {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private ApplicationContext applicationContext;

    @Test
    void greetingShouldReturnDefaultMessage() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/", String.class))
                .isEqualTo("Hello, ua.in.sz.spring.mvc.test.BusinessService");

        log.info("\n\n\n");
        for (String name : Arrays.stream(applicationContext.getBeanDefinitionNames()).sorted().toList()) {
            log.info("Bean name: {}", name);
        }
        log.info("\n\n\n");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
