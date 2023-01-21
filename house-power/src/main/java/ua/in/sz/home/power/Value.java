package ua.in.sz.home.power;

import java.time.LocalDateTime;

public class Value<T> {
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

}
