package ua.in.sz.dsl.model.factories;

import ua.in.sz.dsl.metadata.Type;
import ua.in.sz.dsl.model.Value;

public class Values {
    public static Value values(Type type, String ... values) {
        return Value.builder(type).values(values).build();
    }
}
