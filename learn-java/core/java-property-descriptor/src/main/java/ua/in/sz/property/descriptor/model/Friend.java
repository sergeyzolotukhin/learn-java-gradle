package ua.in.sz.property.descriptor.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Friend {
    private String name;
    private int age;
}
