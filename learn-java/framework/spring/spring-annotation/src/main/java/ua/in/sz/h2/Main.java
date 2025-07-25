package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;

@Slf4j
@Configuration
@PropertySource({"classpath:application.properties", "classpath:second-application.properties"})
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("ua.in.sz.h2");
        context.refresh();

        ConfigurableEnvironment environment = context.getBean(ConfigurableEnvironment.class);

        String value1 = environment.resolvePlaceholders("${my_name}");
        String value2 = environment.getProperty("my_name");
        log.info("1: my.name = [{}]", value1);
        log.info("2: my.name = [{}]", value2);

        BusinessService businessService = context.getBean(BusinessService.class);
        businessService.print();

        SecondBusinessService secondBusinessService = context.getBean(SecondBusinessService.class);
        secondBusinessService.print();

        context.close();
    }

    @Bean
    public SecondBusinessService secondBusinessService() {
        return new SecondBusinessService();
    }
}