package ua.in.sz.dsl.modle;

import org.joda.time.DateTime;
import org.joda.time.Period;
import ua.in.sz.dsl.modle.builder.ScheduleBuilder;
import ua.in.sz.notcomplited.schedules.domain.Resolution;

import java.util.List;

public class Schedule {
    private String type;
    private DateTime start;
    private DateTime end;
    private Period resolution;

    private List<Value> values;

    public Schedule(String type, DateTime start, DateTime end, Period resolution, List<Value> values) {
        this.type = type;
        this.start = start;
        this.end = end;
        this.resolution = resolution;
        this.values = values;
    }

    public static ScheduleBuilder builder(String type, DateTime start, Resolution resolution) {
        return new ScheduleBuilder(type, start, resolution);
    }

    public String getValue(String code, DateTime time) {
        return values.stream()
                .filter(v -> code.equals(v.getCode()))
                .map(v -> v.getValue(time))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

}
