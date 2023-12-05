package ua.in.sz.h2;

import org.springframework.core.Ordered;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public interface ExecutionTimeResolver {
    String STEP = "step";
    String TIME_UNIT = "timeUnit";

    List<LocalDate> resolve(Period period);

    interface WithCondition {
        boolean isSupport();
    }

    interface WithOrder extends Ordered {

    }
}
