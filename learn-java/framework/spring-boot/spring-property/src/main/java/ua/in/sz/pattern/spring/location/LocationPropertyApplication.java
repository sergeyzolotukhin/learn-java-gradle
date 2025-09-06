package ua.in.sz.pattern.spring.location;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@SpringBootApplication
public class LocationPropertyApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(LocationPropertyApplication.class);
        application.run(args);
    }
}
