package ua.in.sz.home.power;

import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Getter
public class Schedule {
    private final LocalDateTime start;
    private final LocalDateTime end;
    private final Duration duration;
    private final List<Value<Object>> values;

    Schedule(LocalDateTime start, LocalDateTime end, Duration duration, List<Value<Object>> values) {
        this.start = start;
        this.end = end;
        this.duration = duration;
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
