package ua.in.sz.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@EnableScheduling
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		// Just a comment
		log.trace("Start application");
		SpringApplication.run(Application.class, args);
	}
}