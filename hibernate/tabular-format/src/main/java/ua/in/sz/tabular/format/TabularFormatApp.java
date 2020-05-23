package ua.in.sz.tabular.format;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.hibernate.xml.impl.NumberScheduleValue;
import ua.in.sz.hibernate.xml.impl.Schedule;
import ua.in.sz.hibernate.xml.impl.StringScheduleValue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TabularFormatApp {
    public static void main(String[] args) {
        BigDecimal value = BigDecimal.valueOf(165465.458751146565465465464654646546544815);

        System.out.println(String.format("| %-30d | %-20s | %-20s | %20.2f |",
                0, "", "", 0.0));
        System.out.println(String.format("| %-30d | %-20s | %-20s | %20.5f |",
                1, "Serhij", "Zolotukhin", value));

        LocalDateTime day = LocalDate.now().atStartOfDay();

        Schedule schedule = Schedule.builder()
                .startDate(day)
                .stopDate(day.plusDays(1))
                .numberValueList(createNumberValues(day, "CLUP"))
                .stringValueList(createStringValues(day, "STST"))
                .build();

        log.info("Message {}", schedule);
    }

    private static List<NumberScheduleValue> createNumberValues(LocalDateTime day, String valueType) {
        List<NumberScheduleValue> values = new ArrayList<>();

        LocalDateTime current = day;
        LocalDateTime endDay = day.plusDays(1);

        while (current.isBefore(endDay)) {
            LocalDateTime next = current.plusMinutes(30);

            values.add(NumberScheduleValue.builder()
                    .type(valueType)
                    .effectiveDay(current)
                    .terminationDay(next)
                    .value(BigDecimal.valueOf(1.2))
                    .build());

            current = next;
        }

        return values;
    }

    private static List<StringScheduleValue> createStringValues(LocalDateTime day, String valueType) {
        List<StringScheduleValue> values = new ArrayList<>();

        LocalDateTime current = day;
        LocalDateTime endDay = day.plusDays(1);

        while (current.isBefore(endDay)) {
            LocalDateTime next = current.plusMinutes(30);

            values.add(StringScheduleValue.builder()
                    .type(valueType)
                    .effectiveDay(current)
                    .terminationDay(next)
                    .value("Text")
                    .build());

            current = next;
        }

        return values;
    }
}
