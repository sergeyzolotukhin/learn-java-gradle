package ua.in.sz.dsl.modle;

import lombok.experimental.UtilityClass;
import org.joda.time.DateTime;
import ua.in.sz.notcomplited.schedules.domain.Resolution;

@UtilityClass
public class Schedules {
    public static ScheduleBuilder dailyFiveMinutes(String type, DateTime day) {
        return Schedule.builder(type, day.withTimeAtStartOfDay(), Resolution.PT5M);
    }
}
