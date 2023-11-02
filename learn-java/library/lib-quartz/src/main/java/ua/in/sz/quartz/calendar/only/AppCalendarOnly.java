package ua.in.sz.quartz.calendar.only;

import lombok.extern.slf4j.Slf4j;
import org.quartz.impl.calendar.CronCalendar;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
public class AppCalendarOnly {
    public static void main(String[] args) throws Exception {
        CronCalendar calendar = new CronCalendar("0 0 0 ? * SAT-SUN");
        WorkdayCalendar workdayCalendar = new WorkdayCalendar("2-10,18-22", calendar);
        CronCalendar wn = new CronCalendar(workdayCalendar, "0 0 0 ? * MON-TUE,THU-SUN");

        log.info("expression: [{}]", workdayCalendar.getExpression());

        LocalDateTime date = LocalDateTime.of(2023, 6, 1, 0, 0, 0);
        int workdayNo = 0;
        for (int i = 0; i < date.toLocalDate().lengthOfMonth(); i++) {
            LocalDateTime localDate = date.plusDays(i);
            ZonedDateTime zdt = ZonedDateTime.of(localDate, ZoneId.systemDefault());
            long tm = zdt.toInstant().toEpochMilli();
            boolean workDay = calendar.isTimeIncluded(tm);
            if (workDay) {
                workdayNo++;
            }
            boolean timeIncluded = wn.isTimeIncluded(tm);

            log.info("{} => {} {} <= {}", localDate.toLocalDate(),
                    workDay ? "1" : "0",
                    timeIncluded ? "1" : "0",
                    workDay ? workdayNo : " "
            );
        }

//        calendar.isTimeIncluded()
    }
}
