package ua.in.sz.hibernate.multiple.sessions;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ua.in.sz.hibernate.multiple.sessions.entities.Attribute;
import ua.in.sz.hibernate.multiple.sessions.entities.Derivation;

import java.lang.reflect.Field;
import java.util.Set;

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

            log.info("Session is started");
            Session session = sessionFactory.openSession();
            Derivation derivation = session.get(Derivation.class, derivationId);
            Set<Attribute> attributes = derivation.getAttributes();
//            log.info("Derivation: {}", derivation);
            log.info("Attributes: {}", defaultToString(attributes));

            log.info("Nested session is started");
            Session nestedSession = sessionFactory.openSession();
            Derivation nestedDerivation = nestedSession.get(Derivation.class, derivationId);
//            Set<Attribute> nestedAttributes = nestedDerivation.getAttributes();

            Field attributesField = Derivation.class.getDeclaredField("attributes");
            attributesField.setAccessible(true);
            attributesField.set(nestedDerivation, attributes);

            Set<Attribute> nestedAttributes = nestedDerivation.getAttributes();
            log.info("Nested attributes: {}", defaultToString(nestedAttributes));

            nestedSession.merge(nestedDerivation);

            nestedSession.close();
            log.info("Nested session is closed");

            for (Attribute attribute : derivation.getAttributes()) {
                log.info("Attributes: {}", attribute);
            }

            session.close();
            log.info("Session closed");


        } catch (Exception e) {
            log.error("Error: ", e);
            // The registry would be destroyed by the SessionFactory, but we
            // had trouble building the SessionFactory so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);

        }
    }

    public static String defaultToString(Object o) {
        return o.getClass().getName() + "@" + Integer.toHexString(o.hashCode());
    }

    private static Long insertDerivation(SessionFactory sessionFactory) {
        Session em = sessionFactory.openSession();

        // model
        Derivation workspace = Derivation.builder().name("Derivation 1").build();
        workspace.add(Attribute.builder().name("Attribute 1").build());
        workspace.add(Attribute.builder().name("Attribute 2").build());

//        log.info("persist");
        em.getTransaction().begin();
        em.persist(workspace);
        em.getTransaction().commit();
        em.clear();

        em.close();

        Long id = workspace.getId();

//        log.info("ID: {}", id);

        return id;
    }
}
