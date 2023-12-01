package ua.in.sz.h2;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import ua.in.sz.h2.ExecutionTimeResolver.WithConditional;
import ua.in.sz.h2.ExecutionTimeResolver.WithStep;

import java.time.Period;
import java.util.Optional;
import java.util.function.Consumer;

@Slf4j
public class ExecutionTimeResolverFactory {
    @Setter
    private ObjectProvider<ExecutionTimeResolver> executionTimeResolvers;

    public Optional<ExecutionTimeResolver> create(Period step) {
        return executionTimeResolvers.orderedStream()
                .peek(withStepIfNecessary(step))
                .peek(this::trace)
                .filter(this::isSupport)
                .findFirst();
    }

    private static Consumer<ExecutionTimeResolver> withStepIfNecessary(Period step) {
        return resolver -> {
            if (resolver instanceof WithStep withStep) withStep.setStep(step);
        };
    }

    private boolean isSupport(ExecutionTimeResolver resolver) {
        return !(resolver instanceof WithConditional conditional)
                || conditional.isSupport();
    }

    private void trace(ExecutionTimeResolver resolver) {
        if (log.isTraceEnabled()) {
            log.info("Class: {}", resolver);
        }
    }
}
