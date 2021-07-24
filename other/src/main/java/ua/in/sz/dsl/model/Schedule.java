package ua.in.sz.dsl.model;

import org.joda.time.DateTime;
import org.joda.time.Period;
import ua.in.sz.dsl.metadata.ScheduleType;
import ua.in.sz.dsl.metadata.Type;
import ua.in.sz.dsl.model.builder.ScheduleBuilder;
import ua.in.sz.notcomplited.schedules.domain.Resolution;

import java.util.List;

public class Schedule {
    private ScheduleType type;
    private DateTime start;
    private DateTime end;
    private Period resolution;

    private List<Param> params;
    private List<Value> values;

    public Schedule(ScheduleType type, DateTime start, DateTime end, Period resolution, List<Value> values, List<Param> params) {
        this.type = type;
        this.start = start;
        this.end = end;
        this.resolution = resolution;
        this.values = values;
        this.params = params;
    }

    public static ScheduleBuilder builder(ScheduleType type, DateTime start, Resolution resolution) {
        return new ScheduleBuilder(type, start, resolution);
    }

    public String getValue(Type type, DateTime time) {
        return values.stream()
                .filter(v -> type.equals(v.getCode()))
                .map(v -> v.getValue(time))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

}
