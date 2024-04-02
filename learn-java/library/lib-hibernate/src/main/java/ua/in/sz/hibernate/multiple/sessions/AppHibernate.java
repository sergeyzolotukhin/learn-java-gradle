package ua.in.sz.hibernate.multiple.sessions;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ua.in.sz.hibernate.multiple.sessions.entities.Attribute;
import ua.in.sz.hibernate.multiple.sessions.entities.Derivation;

import java.util.List;

@Slf4j
public class AppHibernate {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().build();
        try (
                SessionFactory sessionFactory = new MetadataSources(registry)
                        .addAnnotatedClass(Derivation.class)
                        .addAnnotatedClass(Attribute.class)
                        .buildMetadata()
                        .buildSessionFactory()
        ) {
            Session session = sessionFactory.openSession();

            Query<Derivation> q1 = session.createQuery("select m from Derivation m", Derivation.class);
            List<Derivation> l1 = q1.list();

            log.info("Schedule: {}", l1);

            session.close();
        } catch (Exception e) {
            log.error("Error: ", e);
            // The registry would be destroyed by the SessionFactory, but we
            // had trouble building the SessionFactory so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);

        }
    }
}
