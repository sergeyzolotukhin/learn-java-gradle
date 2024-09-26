package ua.in.sz.property.descriptor.model;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
@Builder
public class Person implements Serializable {
    private String name;
    private int age;
    private List<Friend> friends;
}
