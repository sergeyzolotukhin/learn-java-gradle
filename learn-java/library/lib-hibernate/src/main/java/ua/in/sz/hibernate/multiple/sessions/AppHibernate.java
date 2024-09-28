package ua.in.sz.hibernate.multiple.sessions;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import ua.in.sz.hibernate.multiple.sessions.entities.Attribute;
import ua.in.sz.hibernate.multiple.sessions.entities.Derivation;

import java.lang.reflect.Field;
import java.util.Objects;
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
//            Derivation nestedDerivation = nestedSession.get(Derivation.class, derivationId);
            Derivation nestedDerivation = new Derivation();
            Set<Attribute> nestedAttributes = nestedDerivation.getAttributes();

            Field attributesField = Derivation.class.getDeclaredField("attributes");
            attributesField.setAccessible(true);
            attributesField.set(nestedDerivation, attributes);

            nestedDerivation.add(Attribute.builder().name("Attribute 1").build());
            nestedDerivation.add(Attribute.builder().name("Attribute 2").build());

//            Set<Attribute> nestedAttributes = nestedDerivation.getAttributes();
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
        return Objects.toIdentityString(o);
    }

    private static final Logger breakpointLog = org.slf4j.LoggerFactory.getLogger("debug-breakpoint");

    public static boolean logBrackpoint(Object o) {
        breakpointLog.info("close session: [{}]", java.util.Objects.toIdentityString(o));
        return true;
    }

    private static Long insertDerivation(SessionFactory sessionFactory) {
        log.info("insert");
        Session em = sessionFactory.openSession();

        // model
        Derivation derivation = Derivation.builder().name("Derivation 1").build();
        derivation.add(Attribute.builder().name("Attribute 1").build());
        derivation.add(Attribute.builder().name("Attribute 2").build());

//        log.info("persist");
        em.getTransaction().begin();
        em.persist(derivation);
        em.merge(derivation);
        em.getTransaction().commit();
        em.clear();

        em.close();

        Long id = derivation.getId();

//        log.info("ID: {}", id);

        return id;
    }
}
