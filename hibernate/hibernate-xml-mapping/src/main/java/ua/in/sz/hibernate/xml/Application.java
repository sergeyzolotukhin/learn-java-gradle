package ua.in.sz.hibernate.xml;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ua.in.sz.hibernate.xml.impl.NumberScheduleValue;
import ua.in.sz.hibernate.xml.impl.Schedule;
import ua.in.sz.hibernate.xml.impl.StringScheduleValue;
import ua.in.sz.hibernate.xml.impl.Workspace;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;


@SuppressWarnings("unused")
@Slf4j
public class Application {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

//            createWorkspaces(sessionFactory);
//            createSchedules(sessionFactory);

//            findWorkspaces(sessionFactory);
            findSchedules(sessionFactory);
//            findScheduleValues(sessionFactory);

            sessionFactory.close();
        } catch (Exception e) {
            log.error("Can't save or load workspace", e);
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    private static void createSchedules(SessionFactory sessionFactory) {
        doInSession(sessionFactory, (session) ->
        {
            Schedule schedule = Schedule.builder()
                    .identification("Schedule 1")
                    .startDate(LocalDateTime.now())
                    .stopDate(LocalDateTime.now().plusDays(1))
                    .build();

            List<NumberScheduleValue> numberValues = Arrays.asList(
                    NumberScheduleValue.builder()
                            .effectiveDay(LocalDateTime.now())
                            .terminationDay(LocalDateTime.now().plusHours(1))
                            .value(BigDecimal.valueOf(1))
                            .schedule(schedule)
                            .build(),
                    NumberScheduleValue.builder()
                            .effectiveDay(LocalDateTime.now().plusHours(1))
                            .terminationDay(LocalDateTime.now().plusHours(2))
                            .value(BigDecimal.valueOf(2))
                            .schedule(schedule)
                            .build()
            );

            List<StringScheduleValue> stringValues = Arrays.asList(
                    StringScheduleValue.builder()
                            .effectiveDay(LocalDateTime.now())
                            .terminationDay(LocalDateTime.now().plusHours(1))
                            .value("One")
                            .schedule(schedule)
                            .build(),
                    StringScheduleValue.builder()
                            .effectiveDay(LocalDateTime.now().plusHours(1))
                            .terminationDay(LocalDateTime.now().plusHours(2))
                            .value("Two")
                            .schedule(schedule)
                            .build()
            );

            schedule.setNumberValueList(numberValues);
            schedule.setStringValueList(stringValues);

            return session.save(schedule);
        });
    }

    private static void createWorkspaces(SessionFactory sessionFactory) {
        doInSession(sessionFactory, (session) ->
                session.save(Workspace.builder().name("Workspace 3").build()));
    }

    private static void findWorkspaces(SessionFactory sessionFactory) {
        log.info("Find workspaces.");
        Stopwatch stopwatch = Stopwatch.createStarted();

        List<Workspace> workspaces = doInSession(sessionFactory, session ->
                session.createQuery("select w from Workspace w", Workspace.class).list());

        log.info("Found workspaces. count: {}, execution time: {}", CollectionUtils.size(workspaces), stopwatch.stop());

        if (log.isTraceEnabled()) {
            log.trace("Workspace: {}", CollectionUtils.size(workspaces));
        }
    }

    private static void findScheduleValues(SessionFactory sessionFactory) {
        log.info("Find schedules.");
        Stopwatch stopwatch1 = Stopwatch.createStarted();

        List<Long> schedules = doInSession(sessionFactory, session -> {
            List<Long> result = session.createQuery("select s.id from Schedule s", Long.class).list();

            log.trace("Find number values");
            Query<NumberScheduleValue> query = session.createQuery(
                    "select n " +
                            "from NumberScheduleValue n " +
                            "where n.schedule.id in (:ids)",
                    NumberScheduleValue.class);
            query.setParameterList("ids", result);
            List<NumberScheduleValue> values = query.list();

            long numberCount = result.stream()
                    .mapToLong(s -> values.size())
                    .sum();
            log.trace("Schedules number values: {}", numberCount);

            return result;
        });

        log.info("Found schedules. count: {}, execution time: {}", CollectionUtils.size(schedules), stopwatch1.stop());
    }

    private static void findSchedules(SessionFactory sessionFactory) {
        log.info("Find schedules.");
        Stopwatch stopwatch1 = Stopwatch.createStarted();

        List<Schedule> schedules = doInSession(sessionFactory, session -> {
            List<Schedule> result = session.createQuery(
                    "select s " +
                            "from Schedule s ",
                    Schedule.class)
                    .list();
            log.trace("Found schedules: count {}", CollectionUtils.size(result));

            long numberCount = result.stream()
                    .mapToLong(s -> s.getNumberValueList().size())
                    .sum();
            log.trace("Schedules number values: {}", numberCount);

            long stringCount = result.stream()
                    .mapToLong(s -> s.getStringValueList().size())
                    .sum();
            log.trace("Schedules string values: {}", stringCount);

            return result;
        });

        log.info("Found schedules. count: {}, execution time: {}", CollectionUtils.size(schedules), stopwatch1.stop());
    }

    private static <R> R doInSession(SessionFactory sessionFactory, Function<Session, R> function) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            R result = function.apply(session);
            session.getTransaction().commit();

            return result;
        }
    }
}