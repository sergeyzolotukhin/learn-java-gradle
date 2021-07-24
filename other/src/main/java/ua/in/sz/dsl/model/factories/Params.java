package ua.in.sz.dsl.model.factories;

import ua.in.sz.dsl.model.Code;
import ua.in.sz.dsl.model.Param;

public class Params {
    public static Param param(Code type, String value) {
        return Param.builder(type).param(value).build();
    }
}
