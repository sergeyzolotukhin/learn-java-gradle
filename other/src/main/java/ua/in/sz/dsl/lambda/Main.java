package ua.in.sz.dsl.lambda;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import ua.in.sz.dsl.domain.Types;
import ua.in.sz.dsl.model.Schedule;
import ua.in.sz.dsl.model.factories.Params;
import ua.in.sz.dsl.model.factories.Schedules;
import ua.in.sz.dsl.model.factories.Values;

import static ua.in.sz.dsl.domain.Types.AVAILABILITY;
import static ua.in.sz.dsl.domain.Types.FORECAST;

@Slf4j
public class Main {
    public static void main(String[] args) {
        DateTime day = DateTime.now();

        Schedule availability = Schedules.dailyFiveMinutes(AVAILABILITY, day)
                .params(Params.param(AVAILABILITY.RESOURCE, "RO1"))
                .values(Values.values(AVAILABILITY.QUANTITY, "0", "1", "2"),
                        Values.values(AVAILABILITY.PRICE, "0", "1", "2"))
                .build();

        Schedule forecast = Schedules.dailyFiveMinutes(FORECAST, day)
                .params(Params.param(FORECAST.RESOURCE, "RO2"))
                .values(Values.values(FORECAST.VALUE, "0", "1", "2"),
                        Values.values(FORECAST.PRICE, "0", "1", "2"))
                .build();

        String value1 = availability.getValue(AVAILABILITY.QUANTITY, day.withTimeAtStartOfDay().withMinuteOfHour(5));
        log.info("The value of [{}] is [{}]", "A", value1);
    }
}
