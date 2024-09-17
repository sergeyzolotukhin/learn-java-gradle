package ua.in.sz.spring.thread.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.turbo.MatchingFilter;
import ch.qos.logback.core.spi.FilterReply;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.slf4j.Marker;

@Slf4j
@Setter
public class FeatureFilter extends MatchingFilter {
	String MDCKey;
	String value;

	public FilterReply decide(Marker marker, Logger logger, Level level, String format, Object[] params, Throwable t) {
		if (MDCKey == null) {
			return FilterReply.NEUTRAL;
		}

		String value = MDC.get(MDCKey);
		if (value == null) {
			return FilterReply.NEUTRAL;
		}

		if (this.value.equals(value)) {
			return onMatch;
		} else {
			return onMismatch;
		}
	}
}
