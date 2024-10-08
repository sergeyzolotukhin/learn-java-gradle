package ua.in.sz.logging.logs;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.AbstractMatcherFilter;
import ch.qos.logback.core.spi.FilterReply;

public class MdcFilter extends AbstractMatcherFilter<ILoggingEvent> {

	String feature = "";

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getFeature() {
		return feature;
	}

	@Override
	public FilterReply decide(ILoggingEvent event) {
		if (feature.equals(event.getMDCPropertyMap().get(Mdc.FEATURE))) {
			return onMatch;
		} else {
			return onMismatch;
		}
	}
}