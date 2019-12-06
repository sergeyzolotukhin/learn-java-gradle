package ua.in.sz.logging.logs;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.AbstractMatcherFilter;
import ch.qos.logback.core.spi.FilterReply;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Setter
public class MdcFilter extends AbstractMatcherFilter<ILoggingEvent> {

	String feature = StringUtils.EMPTY;

	@Override
	public FilterReply decide(ILoggingEvent event) {
		if (feature.equals(event.getMDCPropertyMap().get(Mdc.FEATURE))) {
			return onMatch;
		} else {
			return onMismatch;
		}
	}
}