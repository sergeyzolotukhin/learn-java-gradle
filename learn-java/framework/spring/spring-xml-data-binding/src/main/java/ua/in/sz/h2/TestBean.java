package ua.in.sz.h2;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
public class TestBean {
    private int anInt;
    private SecondBean second;

    @Data
    public static class SecondBean {
        private String name;
    }
}
