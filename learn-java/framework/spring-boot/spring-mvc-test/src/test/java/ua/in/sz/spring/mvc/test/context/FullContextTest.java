package ua.in.sz.spring.mvc.test.context;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.in.sz.spring.mvc.test.HomeController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FullContextTest {
    @Autowired
    private HomeController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }
}
