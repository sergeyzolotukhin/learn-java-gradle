package ua.in.sz.h2.impl;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.h2.ExecutionTimeResolver;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.List;

@Slf4j
@Setter
@Getter
public class NowExecutionTimeResolver implements ExecutionTimeResolver {
    @Override
    public List<LocalDate> resolve(Period period) {
        LocalDate from = LocalDate.now();
        return Collections.singletonList(from);
    }
}
