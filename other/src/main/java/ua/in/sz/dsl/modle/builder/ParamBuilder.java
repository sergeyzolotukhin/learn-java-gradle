package ua.in.sz.dsl.modle.builder;

import ua.in.sz.dsl.modle.Param;

public class ParamBuilder {
    private String code;
    private String param;

    public ParamBuilder(String code) {
        this.code = code;
    }

    public ParamBuilder param(String param) {
        this.param = param;
        return this;
    }

    public Param build() {
        return new Param(code, param);
    }
}
