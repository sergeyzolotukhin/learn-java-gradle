package ua.in.sz.dsl.modle;

public class Params {
    public static Param param(String type, String value) {
        return Param.builder(type).param(value).build();
    }
}
