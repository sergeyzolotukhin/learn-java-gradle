package ua.in.sz.home.power;

import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
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

    public static class Value<T> {
        private LocalDateTime start;
        private LocalDateTime end;
        private T value;

        Value(LocalDateTime start, LocalDateTime end, T value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }

        public static <T> ValueBuilder<T> builder() {
            return new ValueBuilder<T>();
        }

        public static class ValueBuilder<T> {
            private LocalDateTime start;
            private LocalDateTime end;
            private T value;

            ValueBuilder() {
            }

            public ValueBuilder<T> start(LocalDateTime start) {
                this.start = start;
                return this;
            }

            public ValueBuilder<T> end(LocalDateTime end) {
                this.end = end;
                return this;
            }

            public ValueBuilder<T> value(T value) {
                this.value = value;
                return this;
            }

            public Value<T> build() {
                return new Value<T>(start, end, value);
            }

            public String toString() {
                return "Schedule.Value.ValueBuilder(start=" + this.start + ", end=" + this.end + ", value=" + this.value + ")";
            }
        }
    }

    public static class ScheduleBuilder {
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
            this.values = range(start, end, duration).map(d ->
                            Schedule.Value.builder()
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
            return new Schedule(start, end, values);
        }

        public String toString() {
            return "Schedule.ScheduleBuilder(start=" + this.start + ", end=" + this.end + ", values=" + this.values + ")";
        }
    }

    private static Stream<LocalDateTime> range(LocalDateTime start, LocalDateTime end, Duration duration) {
        Objects.requireNonNull(start, "start");
        Objects.requireNonNull(end, "end");
        Objects.requireNonNull(duration, "duration");

        return Stream.iterate(start, d -> d.isBefore(end), d -> d.plus(duration));
    }
}
