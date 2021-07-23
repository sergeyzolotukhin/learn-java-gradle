package ua.in.sz.dsl.model.factories;

import lombok.experimental.UtilityClass;
import org.joda.time.DateTime;
import ua.in.sz.dsl.model.Code;
import ua.in.sz.dsl.model.Schedule;
import ua.in.sz.dsl.model.builder.ScheduleBuilder;
import ua.in.sz.notcomplited.schedules.domain.Resolution;

@UtilityClass
public class Schedules {
    public static ScheduleBuilder dailyFiveMinutes(Code type, DateTime day) {
        return Schedule.builder(type, day.withTimeAtStartOfDay(), Resolution.PT5M);
    }
}
