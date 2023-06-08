package ua.in.sz.quartz.calendar.only;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Calendar;
import org.quartz.impl.calendar.BaseCalendar;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Slf4j
public class WorkdayCalendar extends BaseCalendar implements Calendar, Serializable {
    private final int holiday;
    @Getter
    private final String expression;

    public WorkdayCalendar(String expression) {
        this(expression, null);
    }

    public WorkdayCalendar(String expression, Calendar baseCalendar) {
        super(baseCalendar);
        this.expression = expression;
        holiday = buildExpression(expression);
    }

    public boolean isTimeIncluded(long timeStamp) {
        if (!getBaseCalendar().isTimeIncluded(timeStamp)) {
            return false;
        }

        LocalDate date = Instant.ofEpochMilli(timeStamp).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate d1 = date.withDayOfMonth(1);

        int workingDay = 0;
        for (int i = 0; i < date.getDayOfMonth(); i++) {
            LocalDate d = d1.plusDays(i);
//            log.info("d of m {}", d);
            long m = d.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
            if (getBaseCalendar().isTimeIncluded(m)) {
                workingDay ++;
            }
        }

//        log.info("work day no {} {}", date,  workingDay);

        int mask = 1 << (workingDay-1);
        int result = holiday & mask;
//        log.info("mask: [{}] result [{}]", Integer.toBinaryString(mask), Integer.toBinaryString(result));
        return result <= 0;
    }

    private int buildExpression(String expression) {
        int holiday = 0;

        String[] rages = expression.split(",");
        for (String rage : rages) {
            String[] bounds = rage.split("-");
            int from = Integer.parseInt(bounds[0]);
            int to = Integer.parseInt(bounds[1]);

            for (int i = from; i <= to; i++) {
                holiday |= 1 << (i - 1);
//                log.info("Range {} -> {}", rage, i);
            }
        }

        log.info("{}", String.format("%32s", Integer.toBinaryString(holiday)).replaceAll(" ", "0"));

        return holiday;
    }
}
