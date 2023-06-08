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
        LocalDate date = Instant.ofEpochMilli(timeStamp).atZone(ZoneId.systemDefault()).toLocalDate();

        int workingDay = 0;
        for (int i = 0; i < date.getDayOfMonth(); i++) {
            LocalDate d = date.plusDays(i);
            long m = d.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
            if (getBaseCalendar().isTimeIncluded(m)) {
                workingDay ++;
            }
        }

        int mask = 1 << workingDay;
        int result = holiday & mask;
//        log.info("mask: [{}] result [{}]", Integer.toBinaryString(mask), Integer.toBinaryString(result));
        return result > 0;
    }

    private int buildExpression(String expression) {
        int holiday = 0;

        String[] rages = expression.split(",");
        for (String rage : rages) {
            String[] bounds = rage.split("-");
            int from = Integer.parseInt(bounds[0]);
            int to = Integer.parseInt(bounds[1]);

            for (int i = from; i <= to; i++) {
                holiday |= 1 << i;
//                log.info("Range {} -> {}", rage, i);
            }
        }

//        String str = String.format("%32s", Integer.toBinaryString(holiday)).replaceAll(" ", "0");
//        log.info("{}", str);

        return holiday;
    }
}
