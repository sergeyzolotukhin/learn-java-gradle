package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;

@Slf4j
@Configuration
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("ua.in.sz.h2");
        context.refresh();

        log.info("singleton -> prototype");
        context.getBean(BusinessService.class).print();
        context.getBean(BusinessService.class).print();

        log.info("prototype");
        context.getBean(PrototypeService.class).print();
        context.getBean(PrototypeService.class).print();

        context.close();
    }
}