package ua.in.sz.pattern.spring.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@SpringBootApplication
public class TestApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TestApplication.class, args);

        TestBusinessService bean = context.getBean(TestBusinessService.class);
        bean.print();
    }
}
