package ua.in.sz.h2;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Person {
    private String name;
    private int age;

    public String getGender() {
        return "gender";
    }

    public void setGender(String gender) {

    }
}
