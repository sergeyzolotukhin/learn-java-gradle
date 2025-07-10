package ua.in.sz.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ImportResource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;

@Slf4j
@SpringBootApplication
@ImportResource("classpath:app-config.xml")
public class MessageSourceApplication {
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(MessageSourceApplication.class, args);
		MessageSource messageSource = ctx.getBean("messageSource", MessageSource.class);
		Object[] arguments = new Object[]{BigDecimal.valueOf(1.2224), LocalDate.now()};
		String message = messageSource.getMessage("message.1", arguments, Locale.getDefault());
		log.info("{}", message);
	}
}