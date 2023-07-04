package ua.in.sz.pattern.spring.camel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@Slf4j
@SpringBootApplication
@ImportResource({"classpath:spring/ftp-servers.xml"})
public class Application  {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
