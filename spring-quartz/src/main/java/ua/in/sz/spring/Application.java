package ua.in.sz.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@Configuration
@EnableScheduling
@EnableAutoConfiguration
public class Application {
	public static void main(String[] args) throws InterruptedException {
		log.info("Start");

		SpringApplication.run(Application.class);

		Thread.sleep(10_000);

		log.info("End");
	}
}