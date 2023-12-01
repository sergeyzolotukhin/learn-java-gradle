package ua.in.sz.h2;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;

import java.time.Period;

@Slf4j
public class ExecutionTimeResolverFactory {
    @Setter
    private ObjectProvider<ExecutionTimeResolver> executionTimeResolvers;

    public ExecutionTimeResolver create(Period step) {

        log.info("Autowire by type");

        executionTimeResolvers.orderedStream()
                .peek(s -> {
                    log.info("Class: {}", s);
                })
                .peek(s -> {
                    if (s instanceof ExecutionTimeResolver.StepAware w) {
                        w.withStep(step);
                    }
                })
                .forEach(s -> {
                    s.resolve(Period.ofMonths(1)).forEach(d -> log.info("Date: {}", d));
                });

        return null;
    }
}
