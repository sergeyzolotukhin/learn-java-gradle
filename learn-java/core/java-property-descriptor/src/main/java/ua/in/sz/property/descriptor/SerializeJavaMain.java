package ua.in.sz.property.descriptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.MultilineRecursiveToStringStyle;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import ua.in.sz.property.descriptor.model.Friend;
import ua.in.sz.property.descriptor.model.Person;

import java.util.Collections;

@Slf4j
public class SerializeJavaMain {
    public static void main(String[] args) throws Exception {
        Friend friendA = Friend.builder()
                .name("Friend A")
                .age(1)
                .build();

        Person person = Person.builder()
                .name("Person A")
                .age(3)
                .friends(Collections.singletonList(friendA))
                .build();

//        ToStringStyle style = ToStringStyle.JSON_STYLE;
        ToStringStyle style = new MultilineRecursiveToStringStyle();
        String string = ReflectionToStringBuilder.toString(person, style, false, false);
        log.info("value: {}", string);
    }
}
