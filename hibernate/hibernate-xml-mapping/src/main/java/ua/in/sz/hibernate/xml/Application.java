package ua.in.sz.hibernate.xml;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ua.in.sz.hibernate.xml.impl.Schedule;
import ua.in.sz.hibernate.xml.impl.Workspace;

import java.util.List;
import java.util.function.Function;


@Slf4j
public class Application {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

            createWorkspaces(sessionFactory);

            findWorkspaces(sessionFactory);

            findSchedules(sessionFactory);

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
            workspaces.forEach(w -> log.trace("Workspace: {}", w));
        }
    }

    private static void findSchedules(SessionFactory sessionFactory) {
        log.info("Find schedules.");
        Stopwatch stopwatch1 = Stopwatch.createStarted();

        List<Schedule> schedules = doInSession(sessionFactory, session ->
                session.createQuery("select s from Schedule s", Schedule.class).list());

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