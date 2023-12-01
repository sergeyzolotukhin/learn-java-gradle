package ua.in.sz.h2.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import ua.in.sz.h2.ExecutionTimeResolver;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.List;

@Slf4j
public class SingleExecutionTimeResolver implements ExecutionTimeResolver, ExecutionTimeResolver.StepAware, Ordered {
    private Period step;

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void withStep(Period step) {
        this.step = step;
    }

    @Override
    public List<LocalDate> resolve(Period period) {
        LocalDate from = LocalDate.now();

        return Collections.singletonList(from.plus(step));
    }
}
