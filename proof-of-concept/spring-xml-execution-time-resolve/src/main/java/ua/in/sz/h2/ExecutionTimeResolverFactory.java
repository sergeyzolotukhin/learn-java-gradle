package ua.in.sz.h2;

import com.google.common.collect.MoreCollectors;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.validation.DataBinder;
import ua.in.sz.h2.ExecutionTimeResolver.WithCondition;

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
                .peek(bindDataIfNecessary(step, timeUnit))
                .peek(this::debug)
                .filter(this::isSupport)
                .collect(MoreCollectors.toOptional());
    }

    private static Consumer<ExecutionTimeResolver> bindDataIfNecessary(Period step, TimeUnit timeUnit) {
        return resolver -> {
            MutablePropertyValues mpv = new MutablePropertyValues();
            mpv.add(ExecutionTimeResolver.STEP, step);
            mpv.add(ExecutionTimeResolver.TIME_UNIT, timeUnit);

            DataBinder db = new DataBinder(resolver, "resolver");
            db.bind(mpv);
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
