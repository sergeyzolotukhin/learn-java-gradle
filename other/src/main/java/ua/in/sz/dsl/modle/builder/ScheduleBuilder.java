package ua.in.sz.dsl.modle.builder;

import org.joda.time.DateTime;
import org.joda.time.Period;
import ua.in.sz.dsl.modle.Schedule;
import ua.in.sz.dsl.modle.Value;
import ua.in.sz.notcomplited.schedules.domain.Resolution;

import java.util.Arrays;
import java.util.List;

public class ScheduleBuilder {
    private String type;
    private DateTime start;
    private DateTime end;
    private Period resolution;
    private List<Value> values;

    public ScheduleBuilder(String type, DateTime start, Resolution resolution) {
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

    public Schedule build() {
        values.forEach(v -> {
            v.setStart(start);
            v.setEnd(end);
            v.setResolution(resolution);
        });

        return new Schedule(type, start, end, resolution, values);
    }
}
