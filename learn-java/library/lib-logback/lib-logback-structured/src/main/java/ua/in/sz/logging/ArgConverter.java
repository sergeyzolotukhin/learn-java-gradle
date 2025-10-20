package ua.in.sz.logging;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.CoreConstants;

import java.util.Objects;
import java.util.Optional;

/**
 * @see ch.qos.logback.classic.pattern.KeyValuePairConverter
 * @see org.springframework.boot.logging.logback.ColorConverter
 */
public class ArgConverter extends ClassicConverter {

    private int index;

    public void start() {
        index = Integer.parseInt(getFirstOption());
        super.start();
    }

    @Override
    public String convert(ILoggingEvent event) {
        Object[] args = event.getArgumentArray();
        if (args == null || args.length < index) {
            return CoreConstants.EMPTY_STRING;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(format(args[index]));
        return sb.toString();
    }

    private String format(Object object) {
        if (object instanceof StructuredApplication app) {
            return format(app);
        } else {
            return Objects.toString(object);
        }
    }

    private String format(StructuredApplication app) {
        return Optional.ofNullable(app)
                .map(a -> "[" + a.getTitle() + " / " + a.getDescription() + "]")
                .orElse("<null>");
    }
}
