package ua.in.sz.quartz.calendar.whole;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Calendar;
import org.quartz.impl.calendar.BaseCalendar;
import ua.in.sz.quartz.calendar.whole.BaseCalendarReference;

@Slf4j
public class TraceCalendar extends BaseCalendar implements BaseCalendarReference {
    private String name;
    private String baseCalendarName;

    public TraceCalendar(String name) {
        this.name = name;
    }

    public TraceCalendar(String name, Calendar baseCalendar) {
        super(baseCalendar);
        this.name = name;
    }

    public TraceCalendar(String name, String baseCalendarName) {
        this.name = name;
        this.baseCalendarName = baseCalendarName;
    }

    @Override
    public String getBaseCalendarName() {
        return baseCalendarName;
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
