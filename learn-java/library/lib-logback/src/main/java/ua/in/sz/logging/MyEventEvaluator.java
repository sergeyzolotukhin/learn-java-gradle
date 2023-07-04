package ua.in.sz.logging;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.ThrowableProxy;
import ch.qos.logback.core.boolex.EventEvaluatorBase;

import java.util.Optional;

public class MyEventEvaluator extends EventEvaluatorBase<ILoggingEvent> {
    /**
     * Do we need to hide stack trace?
     */
    @Override
    public boolean evaluate(ILoggingEvent event) throws NullPointerException {
        return throwable(event)
                .filter(MyEventEvaluator::isMyException)
                .filter(MyEventEvaluator::isMessageEmpty)
                .map(MyEventEvaluator::isStacktraceEmpty)
                .orElse(false);
    }

    private static Optional<Throwable> throwable(ILoggingEvent event) {
        return Optional.ofNullable(event)
                .map(ILoggingEvent::getThrowableProxy)
                .filter(ThrowableProxy.class::isInstance)
                .map(ThrowableProxy.class::cast)
                .map(ThrowableProxy::getThrowable);
    }

    private static boolean isMyException(Throwable throwable) {
        return throwable instanceof MyException;
    }

    private static boolean isMessageEmpty(Throwable throwable) {
        return Optional.ofNullable(throwable)
                .map(Throwable::getMessage)
                .map(String::isEmpty)
                .orElse(false);
    }

    private static boolean isStacktraceEmpty(Throwable throwable) {
        return Optional.ofNullable(throwable)
                .map(Throwable::getStackTrace)
                .map(s -> s.length == 0)
                .orElse(false);
    }
}
