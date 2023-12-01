package ua.in.sz.h2.impl;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.h2.ExecutionTimeResolver;
import ua.in.sz.h2.ExecutionTimeResolver.WithCondition;
import ua.in.sz.h2.ExecutionTimeResolver.WithOrder;
import ua.in.sz.h2.ExecutionTimeResolver.WithStep;
import ua.in.sz.h2.ExecutionTimeResolver.WithTimeUnit;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Getter
@Setter
public class SingleExecutionTimeResolver implements ExecutionTimeResolver, WithStep, WithCondition, WithOrder, WithTimeUnit {
    private Period step;
    private final int order = 0;
    private TimeUnit timeUnit;

    @Override
    public List<LocalDate> resolve(Period period) {
        LocalDate from = LocalDate.now();

        return Collections.singletonList(from.plus(step));
    }

    @Override
    public boolean isSupport() {
        return TimeUnit.MINUTES.equals(timeUnit);
    }
}
