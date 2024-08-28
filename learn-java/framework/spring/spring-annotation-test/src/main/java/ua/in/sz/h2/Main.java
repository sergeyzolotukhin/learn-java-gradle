package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Arrays;

@Slf4j
@Configuration
@PropertySource({"classpath:application.properties"})
public class Main implements BeanDefinitionRegistryPostProcessor {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("ua.in.sz.h2");
        context.refresh();

        for (String name : context.getBeanDefinitionNames()) {
            log.info("Bean name: {}", name);
        }

        context.close();
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        for (int i = 0; i < 400; i++) {
            String beanName = String.format("bean-%d", i);
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(BusinessService.class).setLazyInit(true);
            registry.registerBeanDefinition(beanName, builder.getBeanDefinition());
        }
    }
}