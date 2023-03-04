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

            long d = ChronoUnit.DAYS.between(baseYear, before);
            long d4 = dayNumber(baseYear, before);

            log.info("{} -> {}, {} - {}", before, after, d, d4);

//            log.info("{} -> {}, offset {} -> {}", before, after, transition.getOffsetBefore(), transition.getOffsetAfter());

            transition = rules.nextTransition(transition.getInstant());
        }
    }

    private static long dayNumber(LocalDateTime baseYear, LocalDateTime target) {
        ZoneId zone = ZoneId.of("Europe/Kiev");

        long dayMillis = TimeUnit.DAYS.toMillis(1);
        long baseMillis = baseYear.atZone(zone).toInstant().toEpochMilli();
        long targetMillis = target.toLocalDate().atStartOfDay().atZone(zone).toInstant().toEpochMilli();

        return (targetMillis / dayMillis - baseMillis / dayMillis);
    }
}
