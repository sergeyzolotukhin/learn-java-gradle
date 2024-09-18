package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"application-context.xml", "second-application-context.xml"},
                false);
//        context.setAllowBeanDefinitionOverriding(false);
        context.refresh();

        printMetadata(context);

        PrintService businessService = context.getBean(PrintService.class);
        businessService.print();

        context.close();
    }

    private static void printMetadata(ClassPathXmlApplicationContext context) {
        BeanDefinition beanDefinition = context.getBeanFactory().getBeanDefinition("businessService");
        Object attribute = beanDefinition.getAttribute("attribute-name");
        log.info("attribute-name={}", attribute);

        for (String attributeName : beanDefinition.attributeNames()) {
            log.info("{}={}", attributeName, beanDefinition.getAttribute(attributeName));
        }

        for (String beanDefinitionName : context.getBeanFactory().getBeanDefinitionNames()) {
            log.info("beanDefinitionName={}", beanDefinitionName);
        }
    }
}