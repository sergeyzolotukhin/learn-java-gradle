package ua.in.sz.h2;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import ua.in.sz.h2.ExecutionTimeResolver.WithConditional;

import java.time.Period;
import java.util.Optional;
import java.util.function.Consumer;

@Slf4j
public class ExecutionTimeResolverFactory {
    @Setter
    private ObjectProvider<ExecutionTimeResolver> executionTimeResolvers;

    public Optional<ExecutionTimeResolver> create(Period step) {
        return executionTimeResolvers.orderedStream()
                .peek(s -> log.info("Class: {}", s))
                .peek(withStepIfNecessary(step))
                .filter(this::isSupport)
                .findFirst();
    }

    private static Consumer<ExecutionTimeResolver> withStepIfNecessary(Period step) {
        return s -> {
            if (s instanceof ExecutionTimeResolver.WithStep w) {
                w.setStep(step);
            }
        };
    }

    private boolean isSupport(ExecutionTimeResolver s) {
        return !(s instanceof WithConditional w)
                || w.isSupport();
    }
}
