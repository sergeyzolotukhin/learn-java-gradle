package ua.in.sz.tabular.format;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import ua.in.sz.tabular.domain.Schedule;
import ua.in.sz.tabular.domain.ScheduleValue;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import static java.util.stream.Collectors.*;
import static org.apache.commons.lang3.StringUtils.SPACE;

public class ScheduleLayout extends PatternLayout {

    public static final String NEW_LINE = System.lineSeparator();
    public static final String HORIZONTAL_LINE = StringUtils.rightPad("=", 120, "=");

    @Setter
    private String headerPattern;
    @Setter
    private String footerPattern;

    @Override
    public String doLayout(ILoggingEvent event) {
        StringBuilder sb = new StringBuilder();

        sb.append(super.doLayout(event));

        for (Object arg : event.getArgumentArray()) {
            if (arg instanceof Schedule) {
                Schedule schedule = (Schedule) arg;

                doLayoutHeader(sb, schedule);
                doLayoutValues(sb, schedule);
                doLayoutFooter(sb, schedule);
            }
        }

        return sb.toString();
    }

    // ================================================================================================================
    // layout schedule
    // ================================================================================================================

    private void doLayoutHeader(StringBuilder sb, Schedule schedule) {
        sb.append(HORIZONTAL_LINE).append(NEW_LINE);
        sb.append(schedule.getStartDate()).append(" - ").append(schedule.getStopDate()).append(NEW_LINE);
        sb.append(HORIZONTAL_LINE).append(NEW_LINE);
    }

    private void doLayoutValues(StringBuilder sb, Schedule schedule) {
        valuesByType(schedule.getStringValueList()).forEach(doLayoutValues(sb, getStringValue()));
        valuesByType(schedule.getNumberValueList()).forEach(doLayoutValues(sb, formatNumberValue()));
    }

    private void doLayoutFooter(StringBuilder sb, Schedule schedule) {
        sb.append(HORIZONTAL_LINE).append(NEW_LINE);
    }

    // ================================================================================================================
    // layout schedule values
    // ================================================================================================================

    private <T, V extends ScheduleValue<T>>
    Map<String, List<V>> valuesByType(List<V> values) {
        return values.stream()
                .collect(groupingBy(ScheduleValue::getType,
                        collectingAndThen(toList(), l -> l.stream()
                                .sorted(Comparator.comparing(ScheduleValue::getEffectiveDay))
                                .collect(toList()))));
    }

    private <T, V extends ScheduleValue<T>>
    BiConsumer<String, List<V>> doLayoutValues(StringBuilder sb, Function<T, String> format) {
        return (type, values) -> {
            sb.append(type).append(SPACE);

            String valuesText = values.stream()
                    .map(ScheduleValue::getValue)
                    .map(format)
                    .collect(joining(SPACE));

            sb.append(valuesText).append(NEW_LINE);
        };
    }

    // ================================================================================================================
    // values formatter
    // ================================================================================================================

    private Function<String, String> getStringValue() {
        return v -> v;
    }

    private Function<BigDecimal, String> formatNumberValue() {
        DecimalFormat df = new DecimalFormat();

        return df::format;
    }
}
