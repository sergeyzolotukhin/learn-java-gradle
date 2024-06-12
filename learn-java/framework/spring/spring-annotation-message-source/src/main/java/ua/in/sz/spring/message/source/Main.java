package ua.in.sz.spring.message.source;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setCacheSeconds(1);

        for (int i = 0; i < 60; i++) {
            String message1 = messageSource.getMessage("test", new Object[0], Locale.getDefault());
            log.info("message: {}", message1);

            TimeUnit.MILLISECONDS.sleep(500);
        }

        String message = messageSource.getMessage("test", new Object[0], Locale.of("el"));
        log.info("message: {}", message);
    }
}