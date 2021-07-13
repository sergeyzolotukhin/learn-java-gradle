package ua.in.sz.dsl.modle;

import lombok.Builder;
import org.joda.time.DateTime;
import org.joda.time.Period;

import java.util.List;

@Builder
public class Schedule {
    private DateTime start;
    private DateTime end;
    private Period resolution;

    private List<ValueSchedule> values;

    public String getValue(String code, DateTime time) {
        return values.stream()
                .filter(v -> code.equals(v.getCode()))
                .map(v -> v.getValue(time))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }
}
