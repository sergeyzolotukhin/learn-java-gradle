package ua.in.sz.spring.initialize;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class InitializeApplication {
	public static void main(String[] args) {
		SpringApplication.run(InitializeApplication.class, args);
	}
}