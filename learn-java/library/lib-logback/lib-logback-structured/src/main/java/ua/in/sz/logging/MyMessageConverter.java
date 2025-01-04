package ua.in.sz.logging;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.slf4j.helpers.MessageFormatter;

import java.util.List;

public class MyMessageConverter extends ClassicConverter {
    private String argA;
    private String argB;

    public void start() {
        final List<String> optionList = getOptionList();
        if (optionList == null) {
            addError("at least two options are expected whereas you have declared none");
            return;
        }

        int numOpts = optionList.size();
        if (numOpts < 2) {
            addError("at least two options are expected whereas you have declared only " + numOpts + "as [" + optionList + "]");
            return;
        }

        argA = optionList.get(0);
        argB = optionList.get(1);

        super.start();
    }

    public String convert(ILoggingEvent event) {
        String message = event.getMessage();
        Object[] argumentArray = event.getArgumentArray();

        String formattedMessage;
        if (argumentArray != null) {
            formattedMessage = MessageFormatter.arrayFormat(message, argumentArray).getMessage();
        } else {
            formattedMessage = message;
        }

        return formattedMessage + " arg A=" + argA + " arg B=" + argB;
    }
}
