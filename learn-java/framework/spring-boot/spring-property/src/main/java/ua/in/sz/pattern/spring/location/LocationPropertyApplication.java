package ua.in.sz.pattern.spring.location;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.PropertySource;

@Slf4j
@Configuration
@SpringBootApplication
public class LocationPropertyApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(LocationPropertyApplication.class);
        ConfigurableApplicationContext context = application.run(args);

//        context.getEnvironment().getProperty("");

        for (PropertySource<?> propertySource : context.getEnvironment().getPropertySources()) {
            String name = propertySource.getName();
            Object source = propertySource.getSource();

            log.info("Name: {}, source: {}", name, source.getClass().getSimpleName());
        }
    }
}
