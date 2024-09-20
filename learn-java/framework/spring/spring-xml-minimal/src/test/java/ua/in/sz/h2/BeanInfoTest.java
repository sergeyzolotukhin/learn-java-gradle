package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanInfoFactory;
import org.springframework.beans.ExtendedBeanInfoFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.List;

@Slf4j
public class BeanInfoTest {

    @Test
    void test_1() throws Exception {
        log.info("-------- using JDK Introspector --");
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);
        printPropertyDescriptor(beanInfo.getPropertyDescriptors());

        log.info("-------- using Spring BeanInfoFactory --");
        BeanInfoFactory factory = new ExtendedBeanInfoFactory();
        BeanInfo springBeanInfo = factory.getBeanInfo(Person.class);
        printPropertyDescriptor(springBeanInfo.getPropertyDescriptors());
    }

    private static void printPropertyDescriptor(PropertyDescriptor[] descriptors) {
        for (PropertyDescriptor descriptor : descriptors) {
            log.info("Name: {}", descriptor.getName());
            log.info("Reader: {}", descriptor.getReadMethod());
            log.info("Writer: {}", descriptor.getWriteMethod());
            log.info("");
        }
    }

    @Test
    void setValue() throws Exception {
        Person person = new Person();

        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);
        for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
            if ("descriptions".equals(pd.getName())) {
                Method readMethod = pd.getReadMethod();
                List<String> descriptions = (List<String>) readMethod.invoke(person);
                descriptions.add("Text A");
                descriptions.add("Text B");
            }
        }

        log.info("Descriptions: {}", person.getDescriptions().toString());
    }

}
