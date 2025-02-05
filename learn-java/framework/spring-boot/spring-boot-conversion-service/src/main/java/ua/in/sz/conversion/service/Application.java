package ua.in.sz.conversion.service;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

@Slf4j
@Configuration
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private ConfigurableEnvironment environment;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        context.close();
    }

    @Override
    public void run(String... args) {
        MyDto value = environment.getConversionService().convert("123:321", MyDto.class);
        log.info("{}", value);
    }

    @ToString
    @Getter
    @Builder
    public static class MyDto {
        private Integer value;
        private Integer precision;
        private String origen;
    }
}
