package ua.in.sz.quartz.calendar.only;

import lombok.extern.slf4j.Slf4j;
import org.quartz.impl.calendar.CronCalendar;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
public class AppCalendarOnly {
    public static void main(String[] args) throws Exception {
        CronCalendar calendar = new CronCalendar("0 0 0 ? * MON-FRI");

        LocalDateTime date = LocalDateTime.of(2023, 6, 1, 0, 0, 0);
        for (int i = 0; i < date.toLocalDate().lengthOfMonth(); i++) {
            LocalDateTime localDate = date.plusDays(i);
            ZonedDateTime zdt = ZonedDateTime.of(localDate, ZoneId.systemDefault());
            long tm = zdt.toInstant().toEpochMilli();
//            long nextIncludedTime = calendar.getNextIncludedTime(tm);
            boolean timeIncluded = calendar.isTimeIncluded(tm);

            log.info("{} => {}", localDate.toLocalDate(), timeIncluded);

        }


//        calendar.isTimeIncluded()
    }
}
