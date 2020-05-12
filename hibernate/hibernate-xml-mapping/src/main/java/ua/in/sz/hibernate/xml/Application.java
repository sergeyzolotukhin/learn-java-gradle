package ua.in.sz.hibernate.xml;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ua.in.sz.hibernate.xml.impl.NumberScheduleValue;
import ua.in.sz.hibernate.xml.impl.Schedule;
import ua.in.sz.hibernate.xml.impl.Workspace;

import java.util.List;

import static ua.in.sz.hibernate.xml.Sessions.doInSession;
import static ua.in.sz.hibernate.xml.Sessions.doInStatelessSession;


@SuppressWarnings("unused")
@Slf4j
public class Application {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            MetadataSources metadataSources = new MetadataSources(registry);
            SessionFactory sessionFactory = metadataSources.buildMetadata().buildSessionFactory();

//            findWorkspaces(sessionFactory);
            findSchedules(sessionFactory);
//            findScheduleValues(sessionFactory);

            sessionFactory.close();
        } catch (Exception e) {
            log.error("Can't save or load workspace", e);
            StandardServiceRegistryBuilder.destroy(registry);
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

            int i = 0;
            for (List<Long> chunk : Lists.partition(result, 1000)) {
                log.trace("Find number values");
                Stopwatch stopwatch = Stopwatch.createStarted();

//                session.createNativeQuery("ALTER SESSION SET sql_trace=TRUE").executeUpdate();
//                session.createNativeQuery(String.format("ALTER SESSION SET TRACEFILE_IDENTIFIER = \"SCHEDULE_NUMBER_%d\"", i++)).executeUpdate();

                Query<NumberScheduleValue> query = session.createQuery(
                        "select n " +
                                "from NumberScheduleValue n " +
                                "where n.schedule.id in (:ids)",
                        NumberScheduleValue.class);
                query.setParameterList("ids", chunk);
//                query.setComment("+ MONITOR");
                List<NumberScheduleValue> values = query.list();

                long numberCount = values.size();

//                session.createNativeQuery("ALTER SESSION SET sql_trace=FALSE").executeUpdate();

                log.trace("Schedules number values: {}, time {}", numberCount, stopwatch.stop());
            }

            return result;
        });

        log.info("Found schedules. count: {}, execution time: {}", CollectionUtils.size(schedules), stopwatch1.stop());
    }

    private static void findSchedules(SessionFactory sessionFactory) {
        log.info("Find schedules.");
        Stopwatch stopwatch1 = Stopwatch.createStarted();

        List<Schedule> schedules = doInStatelessSession(sessionFactory, session -> {
            Long count = session.createQuery(
                    "select count (s) " +
                            "from Schedule s " +
                            "WHERE s.workspace.name = '2020-01-01'"
                    , Long.class)
                    .uniqueResult();
            log.info("Found schedule count: {}", count);

            Query<Schedule> query = session.createQuery(
                    "select s " +
                            "from Schedule s " +
                            "LEFT JOIN FETCH s.stringValueSet sv " +
                            "LEFT JOIN FETCH s.numberValueSet nv " +
                            "WHERE s.workspace.name = '2020-01-01'"
                    , Schedule.class);

            List<Schedule> result = query.list();
            log.trace("Found schedules: count {}", CollectionUtils.size(result));

            long numberCount = result.stream()
                    .mapToLong(s -> s.getNumberValueSet().size())
                    .sum();
            log.trace("Schedules number values: {}", numberCount);

            long stringCount = result.stream()
                    .mapToLong(s -> s.getStringValueSet().size())
                    .sum();
            log.trace("Schedules string values: {}", stringCount);

            return result;
        });

        log.info("Found schedules. count: {}, execution time: {}", CollectionUtils.size(schedules), stopwatch1.stop());
    }


}