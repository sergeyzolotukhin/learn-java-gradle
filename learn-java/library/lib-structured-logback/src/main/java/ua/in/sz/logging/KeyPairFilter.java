package ua.in.sz.logging;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.AbstractMatcherFilter;
import ch.qos.logback.core.spi.FilterReply;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class KeyPairFilter extends AbstractMatcherFilter<ILoggingEvent> {

	private String key = "";
	private String value = "";

	@Override
	public FilterReply decide(ILoggingEvent event) {
		if (isMatch(event)) {
			return onMatch;
		} else {
			return onMismatch;
		}
	}

	private boolean isMatch(ILoggingEvent event) {
		return event.getKeyValuePairs().stream()
				.filter(e -> key.equals(e.key))
				.anyMatch(e -> value.equals(e.value));
	}
}