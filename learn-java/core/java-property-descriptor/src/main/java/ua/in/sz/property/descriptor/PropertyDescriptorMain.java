package ua.in.sz.property.descriptor;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.property.descriptor.model.Friend;
import ua.in.sz.property.descriptor.model.Person;
import ua.in.sz.property.descriptor.processor.CleanBeanProcessor;
import ua.in.sz.property.descriptor.processor.MdcBeanProcessor;

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

//        new BeanProcessor().process(personA);
//        new CleanBeanProcessor().process(personA);
        new MdcBeanProcessor().process(personA);
    }
}
