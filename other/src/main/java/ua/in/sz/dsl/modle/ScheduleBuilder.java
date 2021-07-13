package ua.in.sz.dsl.modle;

import org.joda.time.DateTime;
import org.joda.time.Period;
import ua.in.sz.notcomplited.schedules.domain.Resolution;

import java.util.Arrays;
import java.util.List;

public class ScheduleBuilder {
    private String type;
    private DateTime start;
    private DateTime end;
    private Period resolution;
    private List<Value> values;

    ScheduleBuilder(String type, DateTime start, Resolution resolution) {
        this.type = type;
        this.start = start.withTimeAtStartOfDay();
        this.end = this.start.plusDays(1);
        this.resolution = resolution.period();
    }

    public ScheduleBuilder start(DateTime start) {
        this.start = start;
        return this;
    }

    public ScheduleBuilder day(DateTime start) {
        this.start = start.withTimeAtStartOfDay();
        this.end = this.start.plusDays(1);
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

    public String toString() {
        return "Schedule.ScheduleBuilder(start=" + this.start + ", end=" + this.end + ", resolution=" + this.resolution + ", values=" + this.values + ")";
    }
}
