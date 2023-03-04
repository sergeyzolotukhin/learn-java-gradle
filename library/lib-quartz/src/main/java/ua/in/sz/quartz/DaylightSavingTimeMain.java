package ua.in.sz.quartz;

import lombok.extern.slf4j.Slf4j;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.zone.ZoneOffsetTransition;
import java.time.zone.ZoneRules;
import java.util.concurrent.TimeUnit;

// https://stackoverflow.com/questions/1449500/get-daylight-saving-transition-dates-for-time-zones-in-java
@Slf4j
public class DaylightSavingTimeMain {
    public static void main(String[] args) {
        LocalDateTime baseYear = LocalDateTime.of(2023, 1, 1, 0, 0);

        ZoneId zone = ZoneId.of("Europe/Kiev");
        ZoneRules rules = zone.getRules();

        ZonedDateTime now = ZonedDateTime.now(zone);
        ZoneOffsetTransition transition = rules.nextTransition(now.toInstant());
        Instant max = now.plusYears(15).toInstant();
        while (transition != null && transition.getInstant().isBefore(max)) {
//            log.info("{}", transition);

            LocalDateTime before = transition.getDateTimeBefore();
            LocalDateTime after = transition.getDateTimeAfter();

            LocalDate epoch = LocalDate.ofEpochDay(0);
            long d1 = ChronoUnit.DAYS.between(epoch, baseYear);
            long d2 = ChronoUnit.DAYS.between(epoch, before);
            long d = d2 - d1;

            long baseMillis = baseYear.atZone(zone).toInstant().toEpochMilli();
            long millis = before.atZone(zone).toInstant().toEpochMilli();
            long millisPerDay = TimeUnit.DAYS.toMillis(1);
            long d4 = (millis / millisPerDay - baseMillis / millisPerDay) - 1;

            log.info("{} -> {}, {} - {}", before, after, d, d4);

//            log.info("{} -> {}, offset {} -> {}", before, after, transition.getOffsetBefore(), transition.getOffsetAfter());

            transition = rules.nextTransition(transition.getInstant());
        }
    }
}
