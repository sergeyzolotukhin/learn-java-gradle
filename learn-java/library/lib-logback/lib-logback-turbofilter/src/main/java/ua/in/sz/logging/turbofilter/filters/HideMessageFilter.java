package ua.in.sz.logging.turbofilter.filters;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.turbo.MatchingFilter;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.spi.FilterReply;
import lombok.Setter;
import org.slf4j.MDC;
import org.slf4j.Marker;

import java.util.ArrayList;
import java.util.List;

public class HideMessageFilter extends MatchingFilter {
    List<String> formats = new ArrayList<>();

    public HideMessageFilter() {
        this.onMatch = FilterReply.DENY;
    }

    @Override
    public FilterReply decide(Marker marker, Logger logger, Level level, String actualFormat, Object[] params, Throwable t) {
        if (formats == null || actualFormat == null) {
            return FilterReply.NEUTRAL;
        }

        if (formats.contains(actualFormat)) {
            return onMatch;
        } else {
            return onMismatch;
        }
    }

    public void addFormat(String format) {
        formats.add(format);
    }
}
