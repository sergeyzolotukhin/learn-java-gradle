package ua.in.sz.h2.processor.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Friend {
    private String name;
    private int age;
}
