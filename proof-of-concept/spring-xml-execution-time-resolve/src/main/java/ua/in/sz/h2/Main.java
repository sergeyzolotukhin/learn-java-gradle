package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.beans.PropertyDescriptor;
import java.time.Period;

@Slf4j
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"application-context.xml"});

        Period period = Period.ofDays(5);

        ObjectProvider<ExecutionTimeResolver> beanProvider = context.getBeanProvider(ExecutionTimeResolver.class);

        for (ExecutionTimeResolver resolver : beanProvider) {
            log.info("Class: {}", resolver.getClass());

            BeanWrapper beanWrapper = new BeanWrapperImpl(resolver);
            for (PropertyDescriptor propertyDescriptor : beanWrapper.getPropertyDescriptors()) {
                if ("step".equals(propertyDescriptor.getName())) {
                    beanWrapper.setPropertyValue("step", period);
                    log.info("Set property: {}", propertyDescriptor.getName());
                }
            }

            resolver.resolve(Period.ofMonths(1)).forEach(d -> log.info("Date: {}", d));
        }

        context.close();
    }
}