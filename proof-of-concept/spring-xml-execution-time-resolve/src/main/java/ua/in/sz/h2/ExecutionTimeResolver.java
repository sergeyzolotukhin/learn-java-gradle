package ua.in.sz.h2;

import org.springframework.core.Ordered;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public interface ExecutionTimeResolver {
    List<LocalDate> resolve(Period period);

    interface WithStep {
        void setStep(Period step);
    }

    interface WithConditional {
        boolean isSupport();
    }

    interface WithOrder extends Ordered {

    }
}
