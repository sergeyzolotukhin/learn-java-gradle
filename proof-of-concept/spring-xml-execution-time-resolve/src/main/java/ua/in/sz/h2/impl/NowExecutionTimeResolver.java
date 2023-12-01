package ua.in.sz.h2.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import ua.in.sz.h2.ExecutionTimeResolver;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.List;

@Slf4j
public class NowExecutionTimeResolver implements ExecutionTimeResolver, Ordered {
    @Override
    public List<LocalDate> resolve(Period period) {
        LocalDate from = LocalDate.now();
        return Collections.singletonList(from);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
