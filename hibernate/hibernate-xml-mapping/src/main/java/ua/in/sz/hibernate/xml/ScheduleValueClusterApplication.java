package ua.in.sz.hibernate.xml;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ua.in.sz.hibernate.xml.impl.NumberScheduleValue;
import ua.in.sz.hibernate.xml.impl.Schedule;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static ua.in.sz.hibernate.xml.Sessions.doInStatelessSession;

@Slf4j
public class ScheduleValueClusterApplication {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            MetadataSources metadataSources = new MetadataSources(registry);

            SessionFactory sessionFactory = metadataSources.buildMetadata().buildSessionFactory();

            findSchedules(sessionFactory);

            sessionFactory.close();
        } catch (Exception e) {
            log.error("Can't save or load workspace", e);
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    private static void findSchedules(SessionFactory sessionFactory) {
        doInStatelessSession(sessionFactory, session -> {
            log.info("Loading schedules");

            List<Long> workspaceIds = findWorkspaceIds(session, Collections.singletonList("2020-01-01"));

            List<Schedule> schedules = findSchedules(session, workspaceIds);

            List<Long> scheduleIds = schedules.stream().map(Schedule::getId).collect(Collectors.toList());

            List<NumberScheduleValue> numberScheduleValues = findNumberScheduleValues(session, scheduleIds);

            log.info("Loaded schedules, count {}", schedules.size());

            return Collections.emptyList();
        });
    }

    private static List<Long> findWorkspaceIds(StatelessSession session, List<String> names) {
        log.info("Find workspace ids");
        Stopwatch stopwatch = Stopwatch.createStarted();

        List<Long> result = session.createQuery(
                "select w.id " +
                        "from Workspace w " +
                        "where w.name in :names", Long.class)
                .setParameterList("names", names)
                .list();

        log.info("Found workspaces. count: {}, time: {}", CollectionUtils.size(result), stopwatch.stop());

        return result;
    }

    private static List<Schedule> findSchedules(StatelessSession session, List<Long> workspaceIds) {
        log.info("Find schedules");
        Stopwatch stopwatch = Stopwatch.createStarted();

        List<Schedule> result = session.createQuery(
                "select s " +
                        "from Schedule s " +
                        "where s.workspace.id in (:workspaceIds)"
                , Schedule.class)
                .setParameterList("workspaceIds", workspaceIds)
                .list();

        log.info("Found schedules. count: {}, time: {}", CollectionUtils.size(result), stopwatch.stop());

        return result;
    }

    private static List<NumberScheduleValue> findNumberScheduleValues(StatelessSession session, List<Long> scheduleIds) {
        log.info("Find number schedule values");
        Stopwatch stopwatch = Stopwatch.createStarted();

        List<NumberScheduleValue> result = session.createQuery(
                "select n " +
                        "from NumberScheduleValue n " +
                        "where n.schedule.id in (:scheduleIds)"
                , NumberScheduleValue.class)
                .setParameterList("scheduleIds", scheduleIds)
                .list();

        log.info("Found number schedule values. count: {}, time: {}", CollectionUtils.size(result), stopwatch.stop());

        return result;
    }
}