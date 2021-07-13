package ua.in.sz.dsl.modle;

import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.joda.time.Period;
import ua.in.sz.notcomplited.schedules.domain.Resolution;

import java.util.Arrays;
import java.util.List;

public class Value {
    private DateTime start;
    private DateTime end;
    private Period resolution;

    private String code;
    private List<String> values;

    Value(DateTime start, DateTime end, Period resolution, String code, List<String> values) {
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

    public static class ValueBuilder {
        private DateTime start;
        private DateTime end;
        private Period resolution;
        private String code;
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

        ValueBuilder(String code) {
            this.code = code;
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

        public ValueBuilder code(String code) {
            this.code = code;
            return this;
        }

        public ValueBuilder values(String ... values) {
            this.values = Arrays.asList(values);
            return this;
        }

        public Value build() {
            return new Value(start, end, resolution, code, values);
        }

        public String toString() {
            return "ValueSchedule.ValueScheduleBuilder(start=" + this.start + ", end=" + this.end + ", resolution=" + this.resolution + ", code=" + this.code + ", values=" + this.values + ")";
        }
    }
}
