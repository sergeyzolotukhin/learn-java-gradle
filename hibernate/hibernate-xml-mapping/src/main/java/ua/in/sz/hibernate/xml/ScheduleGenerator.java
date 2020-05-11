package ua.in.sz.hibernate.xml;

import ua.in.sz.hibernate.xml.impl.NumberScheduleValue;
import ua.in.sz.hibernate.xml.impl.Schedule;
import ua.in.sz.hibernate.xml.impl.StringScheduleValue;
import ua.in.sz.hibernate.xml.impl.Workspace;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ScheduleGenerator {

    public static final int SCHEDULE_PER_DAY = 2; //10 * 4;
    public static final int NUMBERS_PER_SCHEDULE = 2; //15;
    public static final int STRING_PER_SCHEDULE = 2; //10;
    public static final int INTERVAL_PER_SCHEDULE = 1;//96;

    public static List<Schedule> generate(LocalDateTime startDate, Workspace workspace ) {
        List<Schedule> schedules = new ArrayList<>();

        for (int i = 0; i < SCHEDULE_PER_DAY; i++) {
            Schedule schedule = Schedule.builder()
                    .identification(String.format("Schedule %d", i))
                    .type(String.format("Type %d", i))
                    .startDate(startDate)
                    .stopDate(startDate.plusDays(1))
                    .workspace(workspace)
                    .build();

            schedule.setNumberValueList(createNumberValues(startDate, schedule));
            schedule.setStringValueList(createStringValues(startDate, schedule));

            schedules.add(schedule);
        }

        return schedules;
    }

    private static List<NumberScheduleValue> createNumberValues(LocalDateTime startDate, Schedule schedule) {
        List<NumberScheduleValue> numberValues = new ArrayList<>();

        for (int j = 0; j < NUMBERS_PER_SCHEDULE; j++) {
            for (int i = 0; i < INTERVAL_PER_SCHEDULE; i++) {
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

        for (int j = 0; j < STRING_PER_SCHEDULE; j++) {
            for (int i = 0; i < INTERVAL_PER_SCHEDULE; i++) {
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
