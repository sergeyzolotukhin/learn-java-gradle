package ua.in.sz.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@Slf4j
@SpringBootApplication
@ImportResource("classpath:app-config.xml")
public class BootXmlApplication {
	public static void main(String[] args) {
		SpringApplication.run(BootXmlApplication.class, args);
	}
}