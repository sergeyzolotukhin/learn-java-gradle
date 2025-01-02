package ua.in.sz.hibernate.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class JpaCacheApplication {
	public static void main(String[] args) {
		SpringApplication.run(JpaCacheApplication.class, args);
	}
}