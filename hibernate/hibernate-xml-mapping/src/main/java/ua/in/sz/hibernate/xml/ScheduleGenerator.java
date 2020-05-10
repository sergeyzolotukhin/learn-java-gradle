package ua.in.sz.hibernate.xml;

import ua.in.sz.hibernate.xml.impl.NumberScheduleValue;
import ua.in.sz.hibernate.xml.impl.Schedule;
import ua.in.sz.hibernate.xml.impl.StringScheduleValue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ScheduleGenerator {

    public static Schedule generate() {
        LocalDateTime startDate = LocalDateTime.now();

        Schedule schedule = Schedule.builder()
                .identification("Schedule 1")
                .startDate(startDate)
                .stopDate(startDate.plusDays(1))
                .build();

        schedule.setNumberValueList(createNumberValues(startDate, schedule));
        schedule.setStringValueList(createStringValues(startDate, schedule));

        return schedule;
    }

    private static List<NumberScheduleValue> createNumberValues(LocalDateTime startDate, Schedule schedule) {
        List<NumberScheduleValue> numberValues = new ArrayList<>();

        for (int j = 0; j < 15; j++) {
            for (int i = 0; i < 96; i++) {
                NumberScheduleValue number = NumberScheduleValue.builder()
                        .type(String.format("Number type %d", j))
                        .effectiveDay(startDate.plusMinutes(i * 15))
                        .terminationDay(startDate.plusMinutes((i + 1) * 15))
                        .value(BigDecimal.valueOf(Math.random()))
                        .schedule(schedule)
                        .build();

                numberValues.add(number);
            }
        }

        return numberValues;
    }

    private static List<StringScheduleValue> createStringValues(LocalDateTime startDate, Schedule schedule) {
        List<StringScheduleValue> stringValues = new ArrayList<>();

        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 96; i++) {
                StringScheduleValue string = StringScheduleValue.builder()
                        .type(String.format("String type %d", j))
                        .effectiveDay(startDate.plusMinutes(i * 15))
                        .terminationDay(startDate.plusMinutes((i + 1) * 15))
                        .value(String.format("One %d", i))
                        .schedule(schedule)
                        .build();

                stringValues.add(string);
            }
        }

        return stringValues;
    }
}
