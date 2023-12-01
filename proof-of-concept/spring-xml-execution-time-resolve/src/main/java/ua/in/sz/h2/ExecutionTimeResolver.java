package ua.in.sz.h2;

import org.springframework.core.Ordered;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.concurrent.TimeUnit;

public interface ExecutionTimeResolver {
    List<LocalDate> resolve(Period period);

    interface WithStep {
        void setStep(Period step);
    }

    interface WithTimeUnit {
        void setTimeUnit(TimeUnit timeUnit);
    }

    interface WithCondition {
        boolean isSupport();
    }

    interface WithOrder extends Ordered {

    }
}
