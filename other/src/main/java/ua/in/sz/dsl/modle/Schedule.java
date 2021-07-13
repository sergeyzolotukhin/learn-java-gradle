package ua.in.sz.dsl.modle;

import lombok.Builder;
import org.joda.time.DateTime;

import java.util.Map;

@Builder
public class Schedule {
    private DateTime start;
    private DateTime end;
    private Map<String, ValueSchedule> values;

    public String getValue(String key, DateTime time) {
        return values.get(key).getValue(time);
    }
}
