package ua.in.sz.pattern.spring.property;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Slf4j
@Configuration
@SpringBootApplication
public class Application implements CommandLineRunner {
    private Environment env;

    @Autowired
    public Application(Environment env) {
        this.env = env;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        log.info("Execute: [{}]", env.getProperty("pattern.text"));
        log.info("Execute build in: [{}]", env.getProperty("pattern.build.in.text"));
    }
}
