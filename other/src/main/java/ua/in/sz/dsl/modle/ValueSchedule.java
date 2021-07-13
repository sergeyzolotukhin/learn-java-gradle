package ua.in.sz.dsl.modle;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Getter;
import org.joda.time.*;

import java.util.List;

@Builder
@Getter
public class ValueSchedule {
    private DateTime start;
    private DateTime end;
    private Period resolution;

    private String code;
    private List<String> values;

    public String getValue(DateTime time) {
        Minutes minutes = Minutes.minutesBetween(start, time);
        int i = minutes.getMinutes() / resolution.getMinutes();
        return values.get(i);
    }
}
