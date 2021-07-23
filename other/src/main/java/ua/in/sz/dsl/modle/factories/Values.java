package ua.in.sz.dsl.modle.factories;

import ua.in.sz.dsl.modle.Value;

public class Values {
    public static Value values(String type, String ... values) {
        return Value.builder(type).values(values).build();
    }
}
