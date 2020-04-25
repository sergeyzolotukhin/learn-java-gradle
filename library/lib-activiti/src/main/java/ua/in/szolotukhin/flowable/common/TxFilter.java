package ua.in.szolotukhin.flowable.common;


import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

public class TxFilter extends Filter<ILoggingEvent> {
	@Override
	public FilterReply decide(ILoggingEvent event) {
		String message = event.getMessage();
		if (message.contains("Committing") || message.contains("Closing") || message.contains("Opening")) {
			return FilterReply.ACCEPT;
		}

		return FilterReply.DENY;
	}
}
