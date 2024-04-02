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
            Long derivationId = insertDerivation(sessionFactory);

            Session session = sessionFactory.openSession();
            Derivation derivation = session.get(Derivation.class, derivationId);
            log.info("Derivation: {}, clazz: {}", derivation, derivation.getAttributes().getClass());
            session.close();

        } catch (Exception e) {
            log.error("Error: ", e);
            // The registry would be destroyed by the SessionFactory, but we
            // had trouble building the SessionFactory so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);

        }
    }

    private static Long insertDerivation(SessionFactory sessionFactory) {
        Session em = sessionFactory.openSession();

        // model
        Derivation workspace = Derivation.builder().name("Derivation 1").build();
        workspace.add(Attribute.builder().name("Attribute 1").build());
        workspace.add(Attribute.builder().name("Attribute 2").build());

        log.info("persist");
        em.getTransaction().begin();
        em.persist(workspace);
        em.getTransaction().commit();
        em.clear();

        em.close();

        Long id = workspace.getId();

        log.info("ID: {}", id);

        return id;
    }
}
