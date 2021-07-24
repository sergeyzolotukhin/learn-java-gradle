package ua.in.sz.dsl.model.factories;

import ua.in.sz.dsl.metadata.ParamType;
import ua.in.sz.dsl.model.Param;

public class Params {
    public static Param param(ParamType type, String value) {
        return Param.builder(type).param(value).build();
    }
}
