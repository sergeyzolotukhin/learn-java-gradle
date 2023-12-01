package ua.in.sz.h2;

import com.google.common.collect.MoreCollectors;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import ua.in.sz.h2.ExecutionTimeResolver.WithCondition;
import ua.in.sz.h2.ExecutionTimeResolver.WithStep;
import ua.in.sz.h2.ExecutionTimeResolver.WithTimeUnit;

import java.time.Period;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

@Slf4j
public class ExecutionTimeResolverFactory {
    @Setter
    private ObjectProvider<ExecutionTimeResolver> executionTimeResolvers;

    public Optional<ExecutionTimeResolver> create(Period step, TimeUnit timeUnit) {
        return executionTimeResolvers.orderedStream()
                .peek(withStepIfNecessary(step))
                .peek(withTimeUnitIfNecessary(timeUnit))
                .peek(this::debug)
                .filter(this::isSupport)
                .collect(MoreCollectors.toOptional());
    }

    private static Consumer<ExecutionTimeResolver> withStepIfNecessary(Period step) {
        return resolver -> {
            if (resolver instanceof WithStep withStep) withStep.setStep(step);
        };
    }

    private static Consumer<ExecutionTimeResolver> withTimeUnitIfNecessary(TimeUnit timeUnit) {
        return resolver -> {
            if (resolver instanceof WithTimeUnit withTimeUnit) withTimeUnit.setTimeUnit(timeUnit);
        };
    }

    private boolean isSupport(ExecutionTimeResolver resolver) {
        return !(resolver instanceof WithCondition conditional)
                || conditional.isSupport();
    }

    private void debug(ExecutionTimeResolver resolver) {
        if (log.isDebugEnabled()) {
            log.debug("Class: {}", resolver);
        }
    }
}
