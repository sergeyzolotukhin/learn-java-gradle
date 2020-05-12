package ua.in.sz.hibernate.xml;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import ua.in.sz.hibernate.xml.impl.NumberScheduleValue;
import ua.in.sz.hibernate.xml.impl.Schedule;
import ua.in.sz.hibernate.xml.impl.StringScheduleValue;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static ua.in.sz.hibernate.xml.Sessions.doInStatelessSession;

@Slf4j
public class ScheduleValueClusterApplication {
    public static void main(String[] args) {
        Sessions.doInSessionFactory(ScheduleValueClusterApplication::findSchedules);
    }

    private static void findSchedules(SessionFactory sessionFactory) {
        doInStatelessSession(sessionFactory, session -> {
            log.info("Loading schedules");
            Stopwatch stopwatch = Stopwatch.createStarted();

            List<Long> workspaceIds = findWorkspaceIds(session, Collections.singletonList("2020-01-01"));

            List<Schedule> schedules = findSchedules(session, workspaceIds);

            List<Long> scheduleIds = schedules.stream().map(Schedule::getId).collect(Collectors.toList());

            List<NumberScheduleValue> numberValues = findNumberScheduleValues(session, scheduleIds);
            List<StringScheduleValue> stringValues = findStringScheduleValues(session, scheduleIds);

            log.info("Loaded schedules, count {}, numbers {}, strings {}, time {}",
                    schedules.size(), numberValues.size(), stringValues.size(),
                    stopwatch.stop());

            return Collections.emptyList();
        });
    }

    private static List<Long> findWorkspaceIds(StatelessSession session, List<String> names) {
        log.trace("Find workspace ids");
        Stopwatch stopwatch = Stopwatch.createStarted();

        List<Long> result = session.createQuery(
                "select w.id from Workspace w where w.name in :names", Long.class)
                .setParameterList("names", names)
                .list();

        log.trace("Found workspaces. count: {}, time: {}", CollectionUtils.size(result), stopwatch.stop());

        return result;
    }

    private static List<Schedule> findSchedules(StatelessSession session, List<Long> workspaceIds) {
        log.trace("Find schedules");
        Stopwatch stopwatch = Stopwatch.createStarted();

        List<Schedule> result = session.createQuery(
                "select s from Schedule s where s.workspace.id in (:workspaceIds)"
                , Schedule.class)
                .setParameterList("workspaceIds", workspaceIds)
                .list();

        log.trace("Found schedules. count: {}, time: {}", CollectionUtils.size(result), stopwatch.stop());

        return result;
    }

    private static List<NumberScheduleValue> findNumberScheduleValues(StatelessSession session, List<Long> scheduleIds) {
        log.trace("Find number schedule values");
        Stopwatch stopwatch = Stopwatch.createStarted();

        List<NumberScheduleValue> result = session.createQuery(
                "select n from NumberScheduleValue n where n.schedule.id in (:scheduleIds)"
                , NumberScheduleValue.class)
                .setParameterList("scheduleIds", scheduleIds)
                .list();

        log.trace("Found number schedule values. count: {}, time: {}", CollectionUtils.size(result), stopwatch.stop());

        return result;
    }

    private static List<StringScheduleValue> findStringScheduleValues(StatelessSession session, List<Long> scheduleIds) {
        log.trace("Find string schedule values");
        Stopwatch stopwatch = Stopwatch.createStarted();

        List<StringScheduleValue> result = session.createQuery(
                "select n from StringScheduleValue n where n.schedule.id in (:scheduleIds)"
                , StringScheduleValue.class)
                .setParameterList("scheduleIds", scheduleIds)
                .list();

        log.trace("Found string schedule values. count: {}, time: {}", CollectionUtils.size(result), stopwatch.stop());

        return result;
    }
}