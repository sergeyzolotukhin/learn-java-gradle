package ua.in.sz.property.descriptor.model;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class Friend implements Serializable {
    private String name;
    private int age;
}
