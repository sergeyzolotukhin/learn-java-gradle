package ua.in.sz.hibernate.xml.old;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ua.in.sz.hibernate.xml.impl.Schedule;

import java.util.List;

import static ua.in.sz.hibernate.xml.Sessions.doInStatelessSession;


@Slf4j
public class Application {
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