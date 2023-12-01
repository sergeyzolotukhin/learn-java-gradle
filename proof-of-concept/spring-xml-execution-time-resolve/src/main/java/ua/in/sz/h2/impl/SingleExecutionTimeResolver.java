package ua.in.sz.h2.impl;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.h2.ExecutionTimeResolver;
import ua.in.sz.h2.ExecutionTimeResolver.WithConditional;
import ua.in.sz.h2.ExecutionTimeResolver.WithOrder;
import ua.in.sz.h2.ExecutionTimeResolver.WithStep;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.List;

@Slf4j
@Getter
@Setter
public class SingleExecutionTimeResolver implements ExecutionTimeResolver, WithStep, WithConditional, WithOrder {
    private Period step;
    private final int order = 0;

    @Override
    public List<LocalDate> resolve(Period period) {
        LocalDate from = LocalDate.now();

        return Collections.singletonList(from.plus(step));
    }

    @Override
    public boolean isSupport() {
        return true;
    }
}
