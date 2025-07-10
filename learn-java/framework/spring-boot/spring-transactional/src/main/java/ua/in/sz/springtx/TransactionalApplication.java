package ua.in.sz.springtx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableScheduling
@EnableTransactionManagement
@SpringBootApplication
public class TransactionalApplication {
	public static void main(String[] args) {
		SpringApplication.run(TransactionalApplication.class, args);
	}
}
