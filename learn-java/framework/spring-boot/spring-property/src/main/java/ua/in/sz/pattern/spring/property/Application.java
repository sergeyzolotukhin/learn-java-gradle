package ua.in.sz.pattern.spring.property;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

@Slf4j
@Configuration
@SpringBootApplication
public class Application implements CommandLineRunner {
    private final ConfigurableEnvironment env;

    @Autowired
    public Application(ConfigurableEnvironment env) {
        this.env = env;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        log.info("Execute: [{}]", env.getProperty("pattern.text"));
        log.info("Execute build in: [{}]", env.getProperty("pattern.build.in.text"));
        log.info("Execute profile in: [{}]", env.getProperty("pattern.profile.dev.text"));
        log.info("Execute sz in: [{}]", env.getProperty("szprop"));

        MutablePropertySources propertySources = env.getPropertySources();
        for (PropertySource<?> propertySource : propertySources) {
            log.info("Property sources: {}", propertySource);
        }
    }
}
