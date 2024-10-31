package ua.in.sz.pattern.spring.autowired;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

// org.springframework.beans.factory.support.ConstructorResolver.resolvePreparedArguments
@Slf4j
@Configuration
@SpringBootApplication
public class AutowiredApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AutowiredApplication.class, args);
        ManagerService bean = context.getBean(ManagerService.class);
        bean.print();
    }
}
