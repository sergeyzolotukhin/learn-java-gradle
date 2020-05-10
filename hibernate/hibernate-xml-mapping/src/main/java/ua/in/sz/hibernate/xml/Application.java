package ua.in.sz.hibernate.xml;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ua.in.sz.hibernate.xml.impl.NumberScheduleValue;
import ua.in.sz.hibernate.xml.impl.Schedule;
import ua.in.sz.hibernate.xml.impl.Workspace;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


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
//            findSchedules(sessionFactory);
            findScheduleValues(sessionFactory);

            sessionFactory.close();
        } catch (Exception e) {
            log.error("Can't save or load workspace", e);
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    private static void createSchedules(SessionFactory sessionFactory) {
        LocalDateTime startDate = LocalDateTime.of(2020, 1, 1, 0, 0, 0);

        int DAYS = 12 * 31;
        for (int d = 0; d < DAYS; d++) {
            LocalDateTime date = startDate.plusDays(d);

            log.info("Creating schedule {}", date);

            doInSession(sessionFactory, (session) ->
            {
                List<Schedule> schedules = ScheduleGenerator.generate(date);

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

        List<Long> schedules = doInStatelessSession(sessionFactory, session -> {
            List<Long> result = session.createQuery("select s.id from Schedule s", Long.class).list();

            for (List<Long> chunk : Lists.partition(result, 1000)) {
                log.trace("Find number values");
                Stopwatch stopwatch = Stopwatch.createStarted();

                Query<NumberScheduleValue> query = session.createQuery(
                        "select n " +
                                "from NumberScheduleValue n " +
                                "where n.schedule.id in (:ids)",
                        NumberScheduleValue.class);
                query.setParameterList("ids", chunk);
                List<NumberScheduleValue> values = query.list();

                long numberCount = values.size();

                log.trace("Schedules number values: {}, time {}", numberCount, stopwatch.stop());
            }

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

    private static <R> R doInStatelessSession(SessionFactory sessionFactory, Function<StatelessSession, R> function) {
        try (StatelessSession session = sessionFactory.openStatelessSession()) {
            session.beginTransaction();
            R result = function.apply(session);
            session.getTransaction().commit();

            return result;
        }
    }
}