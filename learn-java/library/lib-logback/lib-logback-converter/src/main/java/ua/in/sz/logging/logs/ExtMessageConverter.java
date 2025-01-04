package ua.in.sz.logging.logs;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * <a href="https://logback.qos.ch/manual/layouts.html#customConversionSpecifier">...</a>
 */
@Slf4j
public class ExtMessageConverter extends MessageConverter {
	@SuppressWarnings("unchecked")
    public String convert(ILoggingEvent event) {
		List<String> formatters = (List<String>) getContext().getObject(FormatterModelHandler.FORMATTER_REGISTRY);
		return event.getFormattedMessage() + "\n formatters: " + formatters;
	}
}
