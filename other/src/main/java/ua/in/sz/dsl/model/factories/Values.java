package ua.in.sz.dsl.model.factories;

import ua.in.sz.dsl.model.Code;
import ua.in.sz.dsl.model.Value;

public class Values {
    public static Value values(Code type, String ... values) {
        return Value.builder(type).values(values).build();
    }
}
