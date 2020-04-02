package ua.in.sz.spring.thread;

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
public class ShowSqlFeatureFilter extends MatchingFilter {
	String MDCKey = "feature";
	String value;
	String loggerName = "org.hibernate.SQL";

	public ShowSqlFeatureFilter() {
		onMatch = FilterReply.ACCEPT;
		onMismatch =FilterReply.DENY;
	}

	@Override
	public FilterReply decide(Marker marker, Logger logger, Level level, String format, Object[] params, Throwable t) {
		if (MDCKey == null) {
			return FilterReply.NEUTRAL;
		}

		String value = MDC.get(MDCKey);

		if (loggerName.equals(logger.getName())) {
			if (this.value.equals(value)) {
				return onMatch;
			} else {
				return onMismatch;
			}
		}

		return FilterReply.NEUTRAL;
	}
}
