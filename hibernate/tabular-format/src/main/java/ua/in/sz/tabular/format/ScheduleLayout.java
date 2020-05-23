package ua.in.sz.tabular.format;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.apache.commons.lang3.StringUtils;
import ua.in.sz.hibernate.xml.impl.NumberScheduleValue;
import ua.in.sz.hibernate.xml.impl.Schedule;
import ua.in.sz.hibernate.xml.impl.ScheduleValue;
import ua.in.sz.hibernate.xml.impl.StringScheduleValue;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import static java.util.stream.Collectors.*;
import static org.apache.commons.lang3.StringUtils.SPACE;

public class ScheduleLayout extends PatternLayout {

    public static final String NEW_LINE = System.lineSeparator();
    public static final String HORIZONTAL_LINE = StringUtils.rightPad("=", 120, "=");

    @Override
    public String doLayout(ILoggingEvent event) {
        StringBuilder sb = new StringBuilder();

        sb.append(super.doLayout(event));

        for (Object arg : event.getArgumentArray()) {
            if (arg instanceof Schedule) {
                Schedule schedule = (Schedule) arg;

                sb.append(HORIZONTAL_LINE).append(NEW_LINE);
                sb.append(schedule.getStartDate()).append(" - ").append(schedule.getStopDate()).append(NEW_LINE);
                sb.append(HORIZONTAL_LINE).append(NEW_LINE);

                stringValuesByType(schedule).forEach(doLayoutStringValues(sb));
                numberValuesByType(schedule).forEach(doLayoutNumberValues(sb));

                sb.append(HORIZONTAL_LINE).append(NEW_LINE);
            }
        }

        return sb.toString();
    }

    // ================================================================================================================
    // layout schedule values
    // ================================================================================================================

    private BiConsumer<String, List<StringScheduleValue>> doLayoutStringValues(StringBuilder sb) {
        return (type, values) -> {
            sb.append(type).append(SPACE);

            String valuesText = values.stream().map(ScheduleValue::getValue).collect(joining(SPACE));
            sb.append(valuesText).append(NEW_LINE);
        };
    }

    private BiConsumer<String, List<NumberScheduleValue>> doLayoutNumberValues(StringBuilder sb) {
        DecimalFormat df = new DecimalFormat();

        return (type, values) -> {
            sb.append(type).append(SPACE);

            String valuesText = values.stream().map(ScheduleValue::getValue).map(df::format).collect(joining(SPACE));
            sb.append(valuesText).append(NEW_LINE);
        };
    }

    // ================================================================================================================
    // Extract values from schedules
    // ================================================================================================================

    private Map<String, List<NumberScheduleValue>> numberValuesByType(Schedule schedule) {
        return schedule.getNumberValueList().stream()
                .collect(groupingBy(ScheduleValue::getType,
                        collectingAndThen(toList(), l -> l.stream()
                                .sorted(Comparator.comparing(ScheduleValue::getEffectiveDay))
                                .collect(toList()))));
    }

    private Map<String, List<StringScheduleValue>> stringValuesByType(Schedule schedule) {
        return schedule.getStringValueList().stream()
                .collect(groupingBy(ScheduleValue::getType,
                        collectingAndThen(toList(), l -> l.stream()
                                .sorted(Comparator.comparing(ScheduleValue::getEffectiveDay))
                                .collect(toList()))));
    }
}
