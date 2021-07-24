package ua.in.sz.dsl.model.builder;

import org.joda.time.DateTime;
import org.joda.time.Period;
import ua.in.sz.dsl.metadata.Type;
import ua.in.sz.dsl.model.Param;
import ua.in.sz.dsl.model.Schedule;
import ua.in.sz.dsl.model.Value;
import ua.in.sz.notcomplited.schedules.domain.Resolution;

import java.util.Arrays;
import java.util.List;

public class ScheduleBuilder {
    private Type type;
    private DateTime start;
    private DateTime end;
    private Period resolution;
    private List<Value> values;
    private List<Param> params;

    public ScheduleBuilder(Type type, DateTime start, Resolution resolution) {
        this.type = type;
        this.resolution = resolution.period();
        this.start = start;
        this.end = this.start.plus(resolution.period());
    }

    public ScheduleBuilder start(DateTime start) {
        this.start = start;
        return this;
    }

    public ScheduleBuilder resolution(Resolution resolution) {
        this.resolution = resolution.period();
        return this;
    }

    public ScheduleBuilder end(DateTime end) {
        this.end = end;
        return this;
    }

    public ScheduleBuilder resolution(Period resolution) {
        this.resolution = resolution;
        return this;
    }

    public ScheduleBuilder values(Value... values) {
        this.values = Arrays.asList(values);
        return this;
    }

    public ScheduleBuilder params(Param... params) {
        this.params = Arrays.asList(params);
        return this;
    }

    public Schedule build() {
        values.forEach(v -> {
            v.setStart(start);
            v.setEnd(end);
            v.setResolution(resolution);
        });

        return new Schedule(type, start, end, resolution, values, params);
    }
}
