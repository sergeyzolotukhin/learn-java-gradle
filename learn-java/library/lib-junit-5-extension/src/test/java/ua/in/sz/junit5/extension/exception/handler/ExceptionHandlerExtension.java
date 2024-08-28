package ua.in.sz.junit5.extension.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

@Slf4j
public class ExceptionHandlerExtension implements TestExecutionExceptionHandler {
    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        log.info("Exception ... We can do something ... ");
        if (throwable instanceof IllegalStateException exception) {
            Assumptions.abort(exception.getMessage());
        }
        throw throwable;
    }
}
