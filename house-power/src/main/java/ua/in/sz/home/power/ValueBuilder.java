package ua.in.sz.home.power;

import java.time.LocalDateTime;

public class ValueBuilder<T> {
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
