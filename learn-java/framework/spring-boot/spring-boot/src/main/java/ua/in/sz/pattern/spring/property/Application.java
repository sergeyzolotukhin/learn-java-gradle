package ua.in.sz.pattern.spring.property;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Slf4j
@Configuration
@SpringBootApplication
public class Application implements CommandLineRunner {
    private final Environment env;

    @Autowired
    public Application(Environment env) {
        this.env = env;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        log.info("\n");

        ConfigurableListableBeanFactory factory = context.getBeanFactory();
        for (String beanDefinitionName : factory.getBeanDefinitionNames()) {
            log.info("beanDefinitionName={}", beanDefinitionName);

            BeanDefinition beanDefinition = factory.getBeanDefinition(beanDefinitionName);
            for (String attributeName : beanDefinition.attributeNames()) {
                log.info("\t\t{}={}", attributeName, beanDefinition.getAttribute(attributeName));
            }
        }

        log.info("\n");

        BusinessService bean = context.getBean(BusinessService.class);
        bean.print();
    }

    @Override
    public void run(String... args) {
        log.info("Execute: [{}]", env.getProperty("pattern.text"));
    }
}
