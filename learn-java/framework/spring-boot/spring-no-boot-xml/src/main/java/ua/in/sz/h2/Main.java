package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.ResourcePropertySource;

import java.io.IOException;
import java.util.Map;

@Slf4j
public class Main {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"application-context.xml"}, false);
        MutablePropertySources propertySources = context.getEnvironment().getPropertySources();
        propertySources.addLast(new ResourcePropertySource("classpath:application.properties"));
        propertySources.addLast(new ResourcePropertySource("classpath:second-application.properties"));
        context.refresh();

        ConfigurableEnvironment environment = context.getBean(ConfigurableEnvironment.class);

        String value1 = environment.resolvePlaceholders("${my_name}");
        String value2 = environment.getProperty("my_name");
        log.info("1: my.name = [{}]", value1);
        log.info("2: my.name = [{}]", value2);

        BusinessService businessService = context.getBean(BusinessService.class);
        businessService.print();

        context.close();
    }
}