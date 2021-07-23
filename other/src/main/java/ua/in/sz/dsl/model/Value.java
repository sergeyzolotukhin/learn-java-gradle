package ua.in.sz.dsl.model;

import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.joda.time.Period;
import ua.in.sz.dsl.model.builder.ValueBuilder;

import java.util.List;

public class Value {
    private DateTime start;
    private DateTime end;
    private Period resolution;

    private String code;
    private List<String> values;

    public Value(DateTime start, DateTime end, Period resolution, String code, List<String> values) {
        this.start = start;
        this.end = end;
        this.resolution = resolution;
        this.code = code;
        this.values = values;
    }

    public static ValueBuilder builder(String code) {
        return new ValueBuilder(code);
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

    public String getCode() {
        return this.code;
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
