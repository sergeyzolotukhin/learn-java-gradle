package ua.in.sz.dsl.domain;

import ua.in.sz.dsl.model.Code;

public enum ScheduleType implements Code {
    AVAILABILITY("AV1"),
    FORECAST("FC1")
    ;

    private final String code;

    ScheduleType(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }
}
