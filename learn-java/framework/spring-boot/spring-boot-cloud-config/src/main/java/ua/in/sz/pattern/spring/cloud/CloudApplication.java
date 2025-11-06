package ua.in.sz.pattern.spring.cloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.PropertySource;

@Slf4j
@SpringBootApplication
public class CloudApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CloudApplication.class, args);
        for (PropertySource<?> propertySource : context.getEnvironment().getPropertySources()) {
            String name = propertySource.getName();
            Object source = propertySource.getSource();

            log.info("Name: {}, source: {}", name, source.getClass().getSimpleName());
        }
    }
}
