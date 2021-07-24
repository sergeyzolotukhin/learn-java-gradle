package ua.in.sz.dsl.model.builder;

import org.joda.time.DateTime;
import org.joda.time.Period;
import ua.in.sz.dsl.model.Type;
import ua.in.sz.dsl.model.Value;
import ua.in.sz.notcomplited.schedules.domain.Resolution;

import java.util.Arrays;
import java.util.List;

public class ValueBuilder {
    private DateTime start;
    private DateTime end;
    private Period resolution;
    private Type type;
    private List<String> values;

    public ValueBuilder day(DateTime start) {
        this.start = start.withTimeAtStartOfDay();
        this.end = this.start.plusDays(1);
        return this;
    }

    public ValueBuilder resolution(Resolution resolution) {
        this.resolution = resolution.period();
        return this;
    }

    public ValueBuilder(Type type) {
        this.type = type;
    }

    public ValueBuilder start(DateTime start) {
        this.start = start;
        return this;
    }

    public ValueBuilder end(DateTime end) {
        this.end = end;
        return this;
    }

    public ValueBuilder resolution(Period resolution) {
        this.resolution = resolution;
        return this;
    }

    public ValueBuilder code(Type type) {
        this.type = type;
        return this;
    }

    public ValueBuilder values(String... values) {
        this.values = Arrays.asList(values);
        return this;
    }

    public Value build() {
        return new Value(start, end, resolution, type, values);
    }
}
