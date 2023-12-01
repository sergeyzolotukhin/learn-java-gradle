package ua.in.sz.h2.impl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.h2.ExecutionTimeResolver;
import ua.in.sz.h2.ExecutionTimeResolver.WithOrder;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.List;

@Slf4j
@Getter
public class NowExecutionTimeResolver implements ExecutionTimeResolver, WithOrder {
    private final int order = 1;

    @Override
    public List<LocalDate> resolve(Period period) {
        LocalDate from = LocalDate.now();
        return Collections.singletonList(from);
    }
}
