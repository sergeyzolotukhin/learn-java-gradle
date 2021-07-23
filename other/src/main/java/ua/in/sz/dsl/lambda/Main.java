package ua.in.sz.dsl.lambda;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import ua.in.sz.dsl.model.factories.Params;
import ua.in.sz.dsl.model.Schedule;
import ua.in.sz.dsl.model.factories.Schedules;
import ua.in.sz.dsl.model.factories.Values;

@Slf4j
public class Main {
    public static void main(String[] args) {
        DateTime day = DateTime.now();

        Schedule scheduleA = Schedules.dailyFiveMinutes("S", day)
                .params(Params.param("A", "RO1"))
                .values(Values.values("A", "0", "1", "2"),
                        Values.values("B", "0", "1", "2"))
                .build();

        Schedule scheduleB = Schedules.dailyFiveMinutes("S", day)
                .params(Params.param("A", "RO2"))
                .values(Values.values("A", "0", "1", "2"),
                        Values.values("B", "0", "1", "2"))
                .build();

        String value1 = scheduleA.getValue("A", day.withTimeAtStartOfDay().withMinuteOfHour(5));
        log.info("The value of [{}] is [{}]", "A", value1);
    }
}
