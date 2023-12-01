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
        for (ExecutionTimeResolver s : executionTimeResolvers) {
            log.info("Class: {}", s);
            if (s instanceof ExecutionTimeResolver.StepAware w) {
                w.withStep(step);
            }
            s.resolve(Period.ofMonths(1)).forEach(d -> log.info("Date: {}", d));
        }

        log.info("Second pass");
        for (ExecutionTimeResolver e : executionTimeResolvers) {
            log.info("Class: {}", e);
        }

        return null;
    }
}
