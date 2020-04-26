package ua.in.sz.logging.logs;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.color.ANSIConstants;
import ch.qos.logback.core.pattern.color.RedCompositeConverter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AddTaskConverter extends RedCompositeConverter<ILoggingEvent> {
	@Override
	protected String getForegroundColorCode(ILoggingEvent event) {
		if (isRequiredHighlight(event)) {
			return ANSIConstants.RED_FG;
		} else {
			return ANSIConstants.DEFAULT_FG;
		}
	}

	private boolean isRequiredHighlight(ILoggingEvent event) {
		return getOptionList().contains(event.getMessage());
	}
}
