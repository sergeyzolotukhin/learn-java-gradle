package ua.in.sz.dsl.model.builder;

import ua.in.sz.dsl.metadata.ParamType;
import ua.in.sz.dsl.metadata.Type;
import ua.in.sz.dsl.model.Param;

public class ParamBuilder {
    private ParamType type;
    private String param;

    public ParamBuilder(ParamType type) {
        this.type = type;
    }

    public ParamBuilder param(String param) {
        this.param = param;
        return this;
    }

    public Param build() {
        return new Param(type, param);
    }
}
