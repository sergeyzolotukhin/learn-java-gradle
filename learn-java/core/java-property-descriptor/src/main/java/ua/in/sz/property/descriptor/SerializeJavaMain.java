package ua.in.sz.property.descriptor;

import ua.in.sz.property.descriptor.model.Friend;
import ua.in.sz.property.descriptor.model.Person;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;

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

        FileOutputStream fileOutputStream = new FileOutputStream("yourfile.log");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(person);
        objectOutputStream.flush();
        objectOutputStream.close();

    }
}
