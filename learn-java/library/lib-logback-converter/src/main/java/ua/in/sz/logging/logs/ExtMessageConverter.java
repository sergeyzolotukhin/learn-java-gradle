package ua.in.sz.logging.logs;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://logback.qos.ch/manual/layouts.html#customConversionSpecifier">...</a>
 */
@Slf4j
public class ExtMessageConverter extends MessageConverter {
	static List<String> formatters = new ArrayList<>();

	public String convert(ILoggingEvent event) {
		return event.getFormattedMessage() + "\n formatters: " + formatters;
	}
}
