package ua.in.sz.dsl.lambda;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import ua.in.sz.dsl.modle.Schedule;
import ua.in.sz.dsl.modle.Schedules;
import ua.in.sz.dsl.modle.Value;

import static ua.in.sz.notcomplited.schedules.domain.Resolution.PT5M;

/*
A name in the plural form IntegrationFlow[s] is return builder IntegrationFlow[Builder].

SourcePollingChannelAdapter[Spec] ->
The base class is the definition IntegrationFlow[Definition] of all builder.

 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        DateTime day = DateTime.now();

        Schedule schedule = Schedules.dailyFiveMinutes("S", day)
                .values(Value.builder("A")
                                .values("0", "1", "2")
                                .build(),
                        Value.builder("B")
                                .values("0", "1", "2")
                                .build())
                .build();

        String value1 = schedule.getValue("A", day.withTimeAtStartOfDay().withMinuteOfHour(5));
        log.info("The value of [{}] is [{}]", "A", value1);
    }
}
