package ua.in.sz.dsl.model;

import ua.in.sz.dsl.model.builder.ParamBuilder;

public class Param {
    private Code code;
    private String param;

    public Param(Code code, String param) {
        this.code = code;
        this.param = param;
    }

    public static ParamBuilder builder(Code code) {
        return new ParamBuilder(code);
    }

    public String getParam() {
        return param;
    }

    public Code getCode() {
        return this.code;
    }
}
