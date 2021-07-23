package ua.in.sz.dsl.modle.factories;

import lombok.experimental.UtilityClass;
import org.joda.time.DateTime;
import ua.in.sz.dsl.modle.Schedule;
import ua.in.sz.dsl.modle.builder.ScheduleBuilder;
import ua.in.sz.notcomplited.schedules.domain.Resolution;

@UtilityClass
public class Schedules {
    public static ScheduleBuilder dailyFiveMinutes(String type, DateTime day) {
        return Schedule.builder(type, day.withTimeAtStartOfDay(), Resolution.PT5M);
    }
}
