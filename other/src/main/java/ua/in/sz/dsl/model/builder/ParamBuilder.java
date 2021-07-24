package ua.in.sz.dsl.model.builder;

import ua.in.sz.dsl.model.Code;
import ua.in.sz.dsl.model.Param;

public class ParamBuilder {
    private Code code;
    private String param;

    public ParamBuilder(Code code) {
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
