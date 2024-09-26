package ua.in.sz.h2.processor;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.h2.processor.model.Friend;
import ua.in.sz.h2.processor.model.Person;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
public class PropertyDescriptorMain {

    public static void main(String[] args) {
        Friend friendA = Friend.builder()
                .name("Friend A")
                .age(1)
                .build();
        Friend friendB = Friend.builder()
                .name("Friend B")
                .age(2)
                .build();

        Friend[] friendArray = {friendA, friendB};

        Person personA = Person.builder()
                .name("Person A")
                .age(3)
                .friends(Collections.singletonList(friendA))
                .build();

        Person personB = Person.builder()
                .name("Person B")
                .age(4)
                .friends(Collections.singletonList(friendB))
                .build();

        List<Friend> friends = IntStream.range(0, 10)
                .mapToObj(i -> Friend.builder()
                        .name(String.format("Friend - %s", i))
                        .age(i)
                        .build())
                .toList();

        List<String> persons = IntStream.range(0, 10)
                .mapToObj(i ->
                        Person.builder()
                                .name(String.format("Name - %s", i))
                                .age(i)
                                .friends(friends)
                                .toString())
                .toList();

        process(personA);
    }

    @SuppressWarnings("unchecked")
    private static void process(Object bean) {
        if (Collection.class.isAssignableFrom(bean.getClass())) {
            processCollection((Collection<Object>) bean, null);
        } else {
            processObject(bean, null);
        }
    }

    private static void processCollection(Collection<?> collection, PropertyDescriptor pd) {
        for (Object object : collection) {
            processObject(object, pd);
        }
    }

    @SneakyThrows
    private static void processObject(Object bean, PropertyDescriptor propertyDescriptor) {
        BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());

        for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
            processProperty(bean, pd);
        }

        doProcessObject(bean);

//        for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
//            processProperty(bean, pd);
//        }
    }

    @SuppressWarnings("unchecked")
    @SneakyThrows
    private static void processProperty(Object bean, PropertyDescriptor pd) {
        if (!isSupported(pd)) {
            return;
        }

        Object propertyValue = pd.getReadMethod().invoke(bean);

//        log.info("pd {}={}", pd.getName(), propertyValue);
        if (Collection.class.isAssignableFrom(propertyValue.getClass())) {
            processCollection((Collection<Object>) propertyValue, pd);
        } else {
            processObject(propertyValue, pd);
        }
    }

    private static void doProcessObject(Object bean) {
        log.info("processObject: {}", bean);
    }

    private static boolean isSupported(PropertyDescriptor pd) {
        return !"class".equals(pd.getName())
                && !pd.getPropertyType().equals(String.class);
    }
}
