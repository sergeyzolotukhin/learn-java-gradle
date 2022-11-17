package ua.in.sz.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

@Slf4j
@SpringBootApplication
@ImportResource("classpath:app-config.xml")
public class Application {
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
		ReloadableResourceBundleMessageSource messageSource = ctx.getBean(ReloadableResourceBundleMessageSource.class);
		String message = messageSource.getMessage("message.1", new Object[0], Locale.getDefault());
		log.info("Message: [{}]", message);
	}
}