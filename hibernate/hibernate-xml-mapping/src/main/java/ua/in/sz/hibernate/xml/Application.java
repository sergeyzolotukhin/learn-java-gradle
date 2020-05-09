package ua.in.sz.hibernate.xml;

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
            List<Workspace> result = session.createQuery("from Workspace", Workspace.class).list();

            log.info("Workspaces count: {}", CollectionUtils.size(result));
            result.forEach(w -> log.info("Workspace: {}", w));

            session.getTransaction().commit();
            session.close();

            sessionFactory.close();
        } catch (Exception e) {
            log.error("Can't save or load workspace", e);
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}