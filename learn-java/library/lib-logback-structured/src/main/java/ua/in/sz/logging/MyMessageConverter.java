package ua.in.sz.logging;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.slf4j.helpers.MessageFormatter;

public class MyMessageConverter extends ClassicConverter {
    public String convert(ILoggingEvent event) {
        String message = event.getMessage();
        Object[] argumentArray = event.getArgumentArray();

        String formattedMessage;
        if (argumentArray != null) {
            formattedMessage = MessageFormatter.arrayFormat(message, argumentArray).getMessage();
        } else {
            formattedMessage = message;
        }

        return formattedMessage + " SZ";
    }
}
