package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.ResourcePropertySource;

import java.io.IOException;
import java.util.Map;

@Slf4j
public class Main {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

        ConfigurableEnvironment environment = context.getBean(ConfigurableEnvironment.class);
        log.info("environment: {}", environment);

//        environment.getPropertySources();
        for (PropertySource<?> propertySource : environment.getPropertySources()) {
            log.info("propertySource: {}", propertySource);
        }

//        String propertyValue = environment.getProperty("my.name");
        String propertyValue = environment.resolvePlaceholders("${my.name}");
        log.info("my.name = [{}]", propertyValue);

        PropertySource<Map<String, Object>> propertySource = new ResourcePropertySource("classpath:application.properties");
        Object value = propertySource.getProperty("my.name");
        log.info("1 my.name = [{}]", value);

        environment.getPropertySources().addLast(propertySource);
        String value2 = environment.getProperty("my.name");
        log.info("2 my.name = [{}]", value2);

//        for (String name : context.getBeanDefinitionNames()) {
//            log.info("Bean: [{}] {}", name, context.getBeanDefinitionCount());
//        }

//        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
//        for (String name : beanFactory.getSingletonNames()) {
//            log.info("2Bean: [{}] {}", name, beanFactory.getSingletonCount());
//        }

        BusinessService businessService = context.getBean(BusinessService.class);
        businessService.print();

        context.close();
    }
}