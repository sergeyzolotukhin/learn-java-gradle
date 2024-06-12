package ua.in.sz.spring.message.source;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

@Slf4j
public class Main {
    public static void main(String[] args) {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setCacheSeconds(10);

        String message = messageSource.getMessage("test", new Object[0], Locale.of("el"));
        log.info("message: {}", message);

        String message1 = messageSource.getMessage("test", new Object[0], Locale.getDefault());
        log.info("message: {}", message1);
    }
}