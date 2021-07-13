package ua.in.sz.dsl.modle;

import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.joda.time.Period;
import ua.in.sz.notcomplited.schedules.domain.Resolution;

import java.util.List;

public class ValueSchedule {
    private DateTime start;
    private DateTime end;
    private Period resolution;

    private String code;
    private List<String> values;

    ValueSchedule(DateTime start, DateTime end, Period resolution, String code, List<String> values) {
        this.start = start;
        this.end = end;
        this.resolution = resolution;
        this.code = code;
        this.values = values;
    }

    public static ValueScheduleBuilder builder() {
        return new ValueScheduleBuilder();
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

    public static class ValueScheduleBuilder {
        private DateTime start;
        private DateTime end;
        private Period resolution;
        private String code;
        private List<String> values;

        public ValueScheduleBuilder day(DateTime start) {
            this.start = start.withTimeAtStartOfDay();
            this.end = this.start.plusDays(1);
            return this;
        }

        public ValueScheduleBuilder resolution(Resolution resolution) {
            this.resolution = resolution.period();
            return this;
        }

        ValueScheduleBuilder() {
        }

        public ValueScheduleBuilder start(DateTime start) {
            this.start = start;
            return this;
        }

        public ValueScheduleBuilder end(DateTime end) {
            this.end = end;
            return this;
        }

        public ValueScheduleBuilder resolution(Period resolution) {
            this.resolution = resolution;
            return this;
        }

        public ValueScheduleBuilder code(String code) {
            this.code = code;
            return this;
        }

        public ValueScheduleBuilder values(List<String> values) {
            this.values = values;
            return this;
        }

        public ValueSchedule build() {
            return new ValueSchedule(start, end, resolution, code, values);
        }

        public String toString() {
            return "ValueSchedule.ValueScheduleBuilder(start=" + this.start + ", end=" + this.end + ", resolution=" + this.resolution + ", code=" + this.code + ", values=" + this.values + ")";
        }
    }
}
