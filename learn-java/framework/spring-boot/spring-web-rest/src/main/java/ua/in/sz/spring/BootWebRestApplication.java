package ua.in.sz.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class BootWebRestApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootWebRestApplication.class, args);
    }
}
