package ua.in.sz.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Calendar;
import org.quartz.impl.calendar.BaseCalendar;

@Slf4j
public class TraceCalendar extends BaseCalendar {
    private String name;

    public TraceCalendar(String name) {
        this(name, null);
    }

    public TraceCalendar(String name, Calendar baseCalendar) {
        super(baseCalendar);
        this.name = name;
    }

    @Override
    public boolean isTimeIncluded(long timeStamp) {
        boolean timeIncluded = super.isTimeIncluded(timeStamp);
        log.info("Is time included {} in calendar [{}]", timeIncluded, name);
        return timeIncluded;
    }

    @Override
    public long getNextIncludedTime(long timeStamp) {
        long nextIncludedTime = super.getNextIncludedTime(timeStamp);
        log.info("next included time: {} in calendar [{}]", nextIncludedTime, name);
        return nextIncludedTime;
    }
}
