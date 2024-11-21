package ua.in.sz.logging.logs;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * <a href="https://logback.qos.ch/manual/layouts.html#customConversionSpecifier">...</a>
 */
@Slf4j
public class ExtMessageConverter extends MessageConverter {
	public String convert(ILoggingEvent event) {
		return event.getFormattedMessage() + " <--- ";
	}
}
