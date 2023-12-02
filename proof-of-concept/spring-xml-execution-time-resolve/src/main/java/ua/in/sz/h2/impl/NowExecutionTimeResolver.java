package ua.in.sz.h2.impl;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.h2.ExecutionTimeResolver;
import ua.in.sz.h2.ExecutionTimeResolver.WithCondition;
import ua.in.sz.h2.ExecutionTimeResolver.WithOrder;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Getter
@Setter
public class NowExecutionTimeResolver implements ExecutionTimeResolver, WithOrder, WithCondition {
    private final int order = 1;
    private TimeUnit timeUnit;

    @Override
    public List<LocalDate> resolve(Period period) {
        LocalDate from = LocalDate.now();
        return Collections.singletonList(from);
    }

    @Override
    public boolean isSupport() {
        return TimeUnit.HOURS.equals(timeUnit);
    }
}
