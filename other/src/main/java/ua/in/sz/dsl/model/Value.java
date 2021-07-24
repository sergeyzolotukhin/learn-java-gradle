package ua.in.sz.dsl.model;

import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.joda.time.Period;
import ua.in.sz.dsl.metadata.Type;
import ua.in.sz.dsl.model.builder.ValueBuilder;

import java.util.List;

public class Value {
    private DateTime start;
    private DateTime end;
    private Period resolution;

    private Type type;
    private List<String> values;

    public Value(DateTime start, DateTime end, Period resolution, Type type, List<String> values) {
        this.start = start;
        this.end = end;
        this.resolution = resolution;
        this.type = type;
        this.values = values;
    }

    public static ValueBuilder builder(Type type) {
        return new ValueBuilder(type);
    }

    public String getValue(DateTime time) {
        Minutes minutes = Minutes.minutesBetween(start, time);
        int i = minutes.getMinutes() / resolution.getMinutes();
        return values.get(i);
    }

    public DateTime getStart() {
        return this.start;
    }

    public DateTime getEnd() {
        return this.end;
    }

    public Period getResolution() {
        return this.resolution;
    }

    public Type getCode() {
        return this.type;
    }

    public List<String> getValues() {
        return this.values;
    }

    public void setStart(DateTime start) {
        this.start = start;
    }

    public void setEnd(DateTime end) {
        this.end = end;
    }

    public void setResolution(Period resolution) {
        this.resolution = resolution;
    }

}
