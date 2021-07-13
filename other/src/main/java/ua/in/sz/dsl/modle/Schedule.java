package ua.in.sz.dsl.modle;

import org.joda.time.DateTime;
import org.joda.time.Period;
import ua.in.sz.notcomplited.schedules.domain.Resolution;

import java.util.List;

public class Schedule {
    private DateTime start;
    private DateTime end;
    private Period resolution;

    private List<ValueSchedule> values;

    Schedule(DateTime start, DateTime end, Period resolution, List<ValueSchedule> values) {
        this.start = start;
        this.end = end;
        this.resolution = resolution;
        this.values = values;
    }

    public static ScheduleBuilder builder() {
        return new ScheduleBuilder();
    }

    public String getValue(String code, DateTime time) {
        return values.stream()
                .filter(v -> code.equals(v.getCode()))
                .map(v -> v.getValue(time))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    public static class ScheduleBuilder {
        private DateTime start;
        private DateTime end;
        private Period resolution;
        private List<ValueSchedule> values;

        ScheduleBuilder() {
        }

        public ScheduleBuilder start(DateTime start) {
            this.start = start;
            return this;
        }

        public ScheduleBuilder day(DateTime start) {
            this.start = start.withTimeAtStartOfDay();
            this.end = this.start.plusDays(1);
            return this;
        }

        public ScheduleBuilder resolution(Resolution resolution) {
            this.resolution = resolution.period();
            return this;
        }


        public ScheduleBuilder end(DateTime end) {
            this.end = end;
            return this;
        }

        public ScheduleBuilder resolution(Period resolution) {
            this.resolution = resolution;
            return this;
        }

        public ScheduleBuilder values(List<ValueSchedule> values) {
            this.values = values;
            return this;
        }

        public Schedule build() {
            values.forEach(v -> {
                v.setStart(start);
                v.setEnd(end);
                v.setResolution(resolution);
            });

            return new Schedule(start, end, resolution, values);
        }

        public String toString() {
            return "Schedule.ScheduleBuilder(start=" + this.start + ", end=" + this.end + ", resolution=" + this.resolution + ", values=" + this.values + ")";
        }
    }
}
