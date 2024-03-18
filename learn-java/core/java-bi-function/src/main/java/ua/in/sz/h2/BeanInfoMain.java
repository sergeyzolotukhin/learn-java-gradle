package ua.in.sz.h2;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ClassUtils;
import org.slf4j.MDC;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class BeanInfoMain {
    public static final String PATH = "path";

    public static void main(String[] args) throws Exception {
        Person person = Person.builder()
                .name("Name 1")
                .description("Description 1")
                .role(Role.builder()
                        .name("role name 1")
                        .build())
                .build();

        processObject(person);
    }

    private static void processObject(Object object) throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
        for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
            String property = propertyDescriptor.getName();

            String origenPath = MDC.get(PATH);
            try {
                MDC.put(PATH, join(origenPath, property));

                Method readMethod = propertyDescriptor.getReadMethod();

                Class<?> propertyType = propertyDescriptor.getPropertyType();
                Object value = readMethod.invoke(object);

                if ("class".equals(property)) {
                    continue;
                }

                if (ClassUtils.isPrimitiveOrWrapper(propertyType)) {
                    log.info("property: {}, value: {}", property, value);
                } else {
                    processObject(value);
                }
            } finally {
                MDC.put(PATH, origenPath);
            }
        }
    }

    private static String join(String... values) {
        return Stream.of(values)
                .filter(Objects::nonNull)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.joining("."));
    }

    @Builder
    @Getter
    public static class Person {
        private final String name;
        private final String description;
        private final Role role;
    }

    @Builder
    @Getter
    public static class Role {
        private final String name;
    }
}
