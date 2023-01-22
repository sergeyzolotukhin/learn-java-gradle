package ua.in.sz.home.power;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

public class ScheduleBuilder {
    private LocalDateTime start;
    private LocalDateTime end;
    private Duration duration;
    private List<Value<Object>> values;

    ScheduleBuilder() {
    }

    public ScheduleBuilder start(LocalDateTime start) {
        this.start = start;
        return this;
    }

    public ScheduleBuilder end(LocalDateTime end) {
        this.end = end;
        return this;
    }

    public ScheduleBuilder duration(Duration duration) {
        this.duration = duration;
        return this;
    }

    public ScheduleBuilder value(Value<Object> value) {
        this.values.add(value);
        return this;
    }

    public ScheduleBuilder values(Function<LocalDateTime, Object> provider) {
        this.values = Schedule.range(start, end, duration).map(d ->
                        Value.builder()
                                .start(d)
                                .end(d.plus(duration))
                                .value(provider.apply(d))
                                .build())
                .toList();
        return this;
    }

    public ScheduleBuilder values(List<Value<Object>> values) {
        this.values = values;
        return this;
    }

    public Schedule build() {
        return new Schedule(start, end, duration, values);
    }

    public String toString() {
        return "Schedule.ScheduleBuilder(start=" + this.start + ", end=" + this.end + ", values=" + this.values + ")";
    }
}
