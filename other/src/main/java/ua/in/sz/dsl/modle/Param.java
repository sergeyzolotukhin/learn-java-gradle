package ua.in.sz.dsl.modle;

import ua.in.sz.dsl.modle.builder.ParamBuilder;

public class Param {
    private String code;
    private String param;

    public Param(String code, String param) {
        this.code = code;
        this.param = param;
    }

    public static ParamBuilder builder(String code) {
        return new ParamBuilder(code);
    }

    public String getParam() {
        return param;
    }

    public String getCode() {
        return this.code;
    }
}
