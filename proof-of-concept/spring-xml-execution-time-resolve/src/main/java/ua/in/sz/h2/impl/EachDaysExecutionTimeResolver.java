package ua.in.sz.h2.impl;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.h2.ExecutionTimeResolver;
import ua.in.sz.h2.ExecutionTimeResolver.WithCondition;
import ua.in.sz.h2.ExecutionTimeResolver.WithStep;
import ua.in.sz.h2.ExecutionTimeResolver.WithTimeUnit;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Setter
@Getter
public class EachDaysExecutionTimeResolver implements ExecutionTimeResolver, WithStep, WithTimeUnit, WithCondition {
    private Period step;
    private TimeUnit timeUnit;

    @Override
    public List<LocalDate> resolve(Period period) {
        LocalDate from = LocalDate.now();
        LocalDate to = from.plus(period);

        return from.datesUntil(to, step).collect(Collectors.toList());
    }

    @Override
    public boolean isSupport() {
        return TimeUnit.DAYS.equals(timeUnit);
    }
}
