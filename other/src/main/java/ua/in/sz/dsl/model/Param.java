package ua.in.sz.dsl.model;

import ua.in.sz.dsl.metadata.ParamType;
import ua.in.sz.dsl.model.builder.ParamBuilder;

public class Param {
    private ParamType type;
    private String param;

    public Param(ParamType type, String param) {
        this.type = type;
        this.param = param;
    }

    public static ParamBuilder builder(ParamType type) {
        return new ParamBuilder(type);
    }

    public String getParam() {
        return param;
    }

    public ParamType getCode() {
        return this.type;
    }
}
