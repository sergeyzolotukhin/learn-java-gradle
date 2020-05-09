package ua.in.sz.hibernate.xml;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ua.in.sz.hibernate.xml.impl.Workspace;

import java.util.List;


@Slf4j
public class Application {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(Workspace.builder().name("Workspace 3").build());
            session.getTransaction().commit();
            session.close();

            session = sessionFactory.openSession();
            session.beginTransaction();

            log.info("Find workspaces.");
            Stopwatch stopwatch = Stopwatch.createStarted();

            List<Workspace> result = session.createQuery("from Workspace", Workspace.class).list();

            log.info("Found workspaces. count: {}, execution time: {}", CollectionUtils.size(result), stopwatch.stop());
            if (log.isTraceEnabled()) {
                result.forEach(w -> log.trace("Workspace: {}", w));
            }

            session.getTransaction().commit();
            session.close();

            sessionFactory.close();
        } catch (Exception e) {
            log.error("Can't save or load workspace", e);
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}