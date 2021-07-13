package ua.in.sz.dsl.lambda;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.kie.soup.commons.util.Maps;
import ua.in.sz.dsl.modle.Schedule;
import ua.in.sz.dsl.modle.ValueSchedule;

import java.util.Collections;

/*
A name in the plural form IntegrationFlow[s] is return builder IntegrationFlow[Builder].

SourcePollingChannelAdapter[Spec] ->
The base class is the definition IntegrationFlow[Definition] of all builder.

 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        ValueSchedule valuesOfA = ValueSchedule.builder()
                .code("A")
                .values(Lists.newArrayList("0", "1", "2"))
                .start(DateTime.parse("2021-07-15T00:00"))
                .end(DateTime.parse("2021-07-16T00:00"))
                .resolution(Period.parse("PT5M"))
                .build();

        ValueSchedule valuesOfB = ValueSchedule.builder()
                .code("B")
                .values(Lists.newArrayList("0", "1", "2"))
                .start(DateTime.parse("2021-07-15T00:00"))
                .end(DateTime.parse("2021-07-16T00:00"))
                .resolution(Period.parse("PT5M"))
                .build();

        Schedule schedule = Schedule.builder()
                .start(DateTime.parse("2021-07-15T00:00"))
                .end(DateTime.parse("2021-07-16T00:00"))
                .resolution(Period.parse("PT5M"))
                .values(Lists.newArrayList(valuesOfA, valuesOfB))
                .build();


//        String value = values.getValue(DateTime.parse("2021-07-15T00:05"));
        String value1 = schedule.getValue("A", DateTime.parse("2021-07-15T00:05"));
        log.info("The value of [{}] is [{}]", "A", value1);
    }




/*    public static void m() {
        StandardIntegrationFlow inputChannel = IntegrationFlows.from(sourceDirectory(), c -> c.poller(Pollers.fixedRate(100)))
                .channel("inputChannel")
                .filter((Integer p) -> p > 0)
                .transform(Object::toString)
                .channel(MessageChannels.queue())
                .get();

        System.out.println(inputChannel);
    }

    public static MessageSource<Object> sourceDirectory() {
        return null;
    }*/
}
