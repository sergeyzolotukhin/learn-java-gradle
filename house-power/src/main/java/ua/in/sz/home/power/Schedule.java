package ua.in.sz.home.power;

import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Schedule {
    @Getter
    private LocalDateTime start;
    @Getter
    private LocalDateTime end;
    @Getter
    private Duration duration;
    @Getter
    private List<Value<Object>> values;

    Schedule(LocalDateTime start, LocalDateTime end, List<Value<Object>> values) {
        this.start = start;
        this.end = end;
        this.values = values;
    }

    public static ScheduleBuilder builder() {
        return new ScheduleBuilder();
    }

    public static Stream<LocalDateTime> range(LocalDateTime start, LocalDateTime end, Duration duration) {
        Objects.requireNonNull(start, "start");
        Objects.requireNonNull(end, "end");
        Objects.requireNonNull(duration, "duration");

        return Stream.iterate(start, d -> d.isBefore(end), d -> d.plus(duration));
    }
}
