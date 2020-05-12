package ua.in.sz.hibernate.xml;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import ua.in.sz.hibernate.xml.impl.NumberScheduleValue;
import ua.in.sz.hibernate.xml.impl.Schedule;
import ua.in.sz.hibernate.xml.impl.StringScheduleValue;
import ua.in.sz.hibernate.xml.impl.Workspace;

import javax.persistence.ParameterMode;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static ua.in.sz.hibernate.xml.Sessions.doInSession;
import static ua.in.sz.hibernate.xml.Sessions.doInStatelessSession;

@Slf4j
public class ScheduleGeneratorApplication {

    public static final int DAYS = 10;
    public static final int SCHEDULE_PER_DAY = 100 * 4;
    public static final int NUMBERS_PER_SCHEDULE = 15;
    public static final int STRING_PER_SCHEDULE = 10;
    public static final int INTERVAL_PER_SCHEDULE = 96;

    public static void main(String[] args) {
        Sessions.doInSessionFactory(sessionFactory -> {
            createSchedules(sessionFactory);
//            gatherStats(sessionFactory);
        });
    }

    private static void createSchedules(SessionFactory sessionFactory) {
        LocalDateTime startDate = LocalDateTime.of(2020, 1, 1, 0, 0, 0);

        for (int d = 0; d < DAYS; d++) {
            LocalDateTime date = startDate.plusDays(d);

            log.info("Creating schedule {}", date);

            doInSession(sessionFactory, (session) ->
            {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                Workspace workspace = Workspace.builder().name(formatter.format(date)).build();
                session.save(workspace);

                List<Schedule> schedules = generate(date, workspace);

                for (int i = 0; i < schedules.size(); i++) {
                    session.save(schedules.get(i));

                    if (i % 50 == 0) {
                        session.flush();
                        session.clear();
                    }
                }

                session.flush();
                session.clear();

                return null;
            });

            log.info("Created schedule {}", date);
        }
    }

    private static void gatherStats(SessionFactory sessionFactory) {
        doInStatelessSession(sessionFactory, session -> {
            log.info("Gathering stats");
            Stopwatch stopwatch = Stopwatch.createStarted();

            session.createStoredProcedureCall("DBMS_STATS.GATHER_SCHEMA_STATS")
                    .registerStoredProcedureParameter("ownname", String.class, ParameterMode.IN)
                    .setParameter("ownname", "GE_DEV02")
                    .execute();

            log.info("Gathered stats. Execution time: {}", stopwatch.stop());

            return null;
        });
    }

    // ================================================================================================================
    //
    // ================================================================================================================

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
