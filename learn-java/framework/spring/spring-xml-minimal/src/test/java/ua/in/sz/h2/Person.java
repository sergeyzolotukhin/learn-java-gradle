package ua.in.sz.h2;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Person {
    private String name;
    private int age;
    private List<String> descriptions = new ArrayList<>();

    public String getGender() {
        return "gender";
    }

    public void setGender(String gender) {

    }
}
