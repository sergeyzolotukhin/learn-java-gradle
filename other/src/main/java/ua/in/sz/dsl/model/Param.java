package ua.in.sz.dsl.model;

import ua.in.sz.dsl.metadata.Type;
import ua.in.sz.dsl.model.builder.ParamBuilder;

public class Param {
    private Type type;
    private String param;

    public Param(Type type, String param) {
        this.type = type;
        this.param = param;
    }

    public static ParamBuilder builder(Type type) {
        return new ParamBuilder(type);
    }

    public String getParam() {
        return param;
    }

    public Type getCode() {
        return this.type;
    }
}
