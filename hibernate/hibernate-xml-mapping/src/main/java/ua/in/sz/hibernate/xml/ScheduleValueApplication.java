package ua.in.sz.hibernate.xml;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ua.in.sz.hibernate.xml.impl.NumberScheduleValue;
import ua.in.sz.hibernate.xml.impl.Schedule;
import ua.in.sz.hibernate.xml.impl.StringScheduleValue;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static ua.in.sz.hibernate.xml.Sessions.doInSession;

@Slf4j
public class ScheduleValueApplication {
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
        doInSession(sessionFactory, session -> {
            log.info("Loading schedules");
            Stopwatch stopwatch = Stopwatch.createStarted();

            List<Long> workspaceIds = findWorkspaceIds(session, Collections.singletonList("2020-01-01"));

            List<Schedule> schedules = findSchedules(session, workspaceIds);

            List<NumberScheduleValue> numberValues = findNumberScheduleValues(schedules);
            List<StringScheduleValue> stringValues = findStringScheduleValues(schedules);

            log.info("Loaded schedules, count {}, numbers {}, strings {}, time {}",
                    schedules.size(), numberValues.size(), stringValues.size(),
                    stopwatch.stop());

            return Collections.emptyList();
        });
    }

    private static List<Long> findWorkspaceIds(Session session, List<String> names) {
        log.trace("Find workspace ids");
        Stopwatch stopwatch = Stopwatch.createStarted();

        List<Long> result = session.createQuery(
                "select w.id from Workspace w where w.name in :names", Long.class)
                .setParameterList("names", names)
                .setReadOnly(true)
                .list();

        log.trace("Found workspaces. count: {}, time: {}", CollectionUtils.size(result), stopwatch.stop());

        return result;
    }

    private static List<Schedule> findSchedules(Session session, List<Long> workspaceIds) {
        log.trace("Find schedules");
        Stopwatch stopwatch = Stopwatch.createStarted();

        List<Schedule> result = session.createQuery(
                "select s from Schedule s where s.workspace.id in (:workspaceIds)"
                , Schedule.class)
                .setParameterList("workspaceIds", workspaceIds)
                .setReadOnly(true)
                .list();

        log.trace("Found schedules. count: {}, time: {}", CollectionUtils.size(result), stopwatch.stop());

        return result;
    }

    private static List<NumberScheduleValue> findNumberScheduleValues(List<Schedule> schedules) {
        log.trace("Find number schedule values");
        Stopwatch stopwatch = Stopwatch.createStarted();

        List<NumberScheduleValue> result = schedules.stream()
                .flatMap(schedule -> schedule.getNumberValueList().stream())
                .collect(Collectors.toList());

        log.trace("Found number schedule values. count: {}, time: {}", CollectionUtils.size(result), stopwatch.stop());

        return result;
    }

    private static List<StringScheduleValue> findStringScheduleValues(List<Schedule> schedules ) {
        log.trace("Find string schedule values");
        Stopwatch stopwatch = Stopwatch.createStarted();

        List<StringScheduleValue> result = schedules.stream()
                .flatMap(schedule -> schedule.getStringValueList().stream())
                .collect(Collectors.toList());

        log.trace("Found string schedule values. count: {}, time: {}", CollectionUtils.size(result), stopwatch.stop());

        return result;
    }
}