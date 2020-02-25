package ua.in.sz.spring.bean.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@SpringBootApplication
public class Application  {
	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
