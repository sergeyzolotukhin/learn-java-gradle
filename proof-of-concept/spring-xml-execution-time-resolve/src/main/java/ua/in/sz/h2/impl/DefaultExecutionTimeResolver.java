package ua.in.sz.h2.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.h2.ExecutionTimeResolver;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class DefaultExecutionTimeResolver implements ExecutionTimeResolver {
    private final Period step;

    @Override
    public List<LocalDate> resolve(Period period) {
        LocalDate from = LocalDate.now();
        LocalDate to = from.plus(period);

        return from.datesUntil(to, step).collect(Collectors.toList());
    }
}
