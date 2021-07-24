package ua.in.sz.dsl.lambda;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import ua.in.sz.dsl.domain.Types;
import ua.in.sz.dsl.model.Schedule;
import ua.in.sz.dsl.model.factories.Params;
import ua.in.sz.dsl.model.factories.Schedules;
import ua.in.sz.dsl.model.factories.Values;

@Slf4j
public class Main {
    public static void main(String[] args) {
        DateTime day = DateTime.now();

        Schedule availability = Schedules.dailyFiveMinutes(Types.AVAILABILITY, day)
                .params(Params.param(Types.AVAILABILITY.RESOURCE, "RO1"))
                .values(Values.values(Types.AVAILABILITY.QUANTITY, "0", "1", "2"),
                        Values.values(Types.AVAILABILITY.PRICE, "0", "1", "2"))
                .build();

        Schedule forecast = Schedules.dailyFiveMinutes(Types.FORECAST, day)
                .params(Params.param(Types.FORECAST.RESOURCE, "RO2"))
                .values(Values.values(Types.FORECAST.VALUE, "0", "1", "2"),
                        Values.values(Types.FORECAST.PRICE, "0", "1", "2"))
                .build();

        String value1 = availability.getValue(Types.AVAILABILITY.QUANTITY, day.withTimeAtStartOfDay().withMinuteOfHour(5));
        log.info("The value of [{}] is [{}]", "A", value1);
    }
}
