package ua.in.sz.h2.processor.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Person {
    private String name;
    private int age;
    private List<Friend> friends;
}
