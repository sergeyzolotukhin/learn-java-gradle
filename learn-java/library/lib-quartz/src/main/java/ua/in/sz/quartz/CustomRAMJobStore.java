package ua.in.sz.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Calendar;
import org.quartz.simpl.RAMJobStore;
import ua.in.sz.quartz.calendar.whole.BaseCalendarReference;

@Slf4j
public class CustomRAMJobStore extends RAMJobStore {
    @Override
    public Calendar retrieveCalendar(String calendarName) {
        log.info("retrieve calendar [{}]", calendarName);

        Calendar calendar = super.retrieveCalendar(calendarName);

        if (calendar instanceof BaseCalendarReference) {
            String baseCalendarName = ((BaseCalendarReference) calendar).getBaseCalendarName();
            if (baseCalendarName != null) {
                calendar.setBaseCalendar(retrieveCalendar(baseCalendarName));
            }
        }

        return calendar;
    }
}
