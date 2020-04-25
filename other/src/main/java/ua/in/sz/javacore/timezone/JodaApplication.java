package ua.in.sz.javacore.timezone;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Interval;

import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

@Slf4j
public class JodaApplication {
    public static void main(String[] args) {
        log.info("Java GMT Timezone offset: {} hours",
                TimeUnit.MILLISECONDS.toHours(TimeZone.getTimeZone("GMT").getRawOffset()));
        log.info("Java CET Timezone offset: {} hours",
                TimeUnit.MILLISECONDS.toHours(TimeZone.getTimeZone("CET").getRawOffset()));

        DateTime dateGmt = DateTime.now(DateTimeZone.forID("GMT"));
        DateTime dateCet = DateTime.now(DateTimeZone.forID("CET"));

        log.info("GMT date: {}", dateGmt);
        log.info("CET date: {}", dateCet);

        Interval gmtInterval = new Interval(dateGmt, dateGmt.plusDays(1));
        Interval cetInterval = new Interval(dateCet, dateCet.plusDays(1));

        log.info("GMT interval: {}", gmtInterval);
        log.info("CET interval: {}", cetInterval);
    }
}
