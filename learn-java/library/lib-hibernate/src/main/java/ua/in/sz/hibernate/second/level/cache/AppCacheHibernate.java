package ua.in.sz.hibernate.second.level.cache;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.MDC;
import ua.in.sz.hibernate.second.level.cache.entities.Attribute;
import ua.in.sz.hibernate.second.level.cache.entities.Derivation;

import java.util.Set;

@Slf4j
public class AppCacheHibernate {

    public static final String MDC_STEP = "step";

    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .loadProperties("hibernate-cache.properties")
                .build();

        try (
                SessionFactory sessionFactory = buildSessionFactory(registry);
        ) {
            Long derivationId = insertDerivation(sessionFactory);

            selectDerivation(sessionFactory, derivationId);

            log.info("Second level cache hits: {}" , sessionFactory.getStatistics().getSecondLevelCacheHitCount());
            log.info("Second level cache misses: {}" , sessionFactory.getStatistics().getSecondLevelCacheMissCount());

//            sessionFactory.getStatistics().logSummary();
        } catch (Exception e) {
            log.error("Error: ", e);
            // The registry would be destroyed by the SessionFactory, but we
            // had trouble building the SessionFactory so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);

        }
    }

    private static SessionFactory buildSessionFactory(ServiceRegistry registry) {
        try (MDC.MDCCloseable ignored = MDC.putCloseable(MDC_STEP, "build")) {
            return new MetadataSources(registry)
                    .addAnnotatedClass(Derivation.class)
                    .addAnnotatedClass(Attribute.class)
                    .buildMetadata()
                    .buildSessionFactory();
        }
    }

    private static void selectDerivation(SessionFactory sessionFactory, Long derivationId) {
        try (MDC.MDCCloseable ignored = MDC.putCloseable(MDC_STEP, "select")) {
            Session session;
            Derivation derivation;
            Set<Attribute> attributes;

            log.info("start session 1");
            session = sessionFactory.openSession();
            derivation = session.get(Derivation.class, derivationId);
            attributes = derivation.getAttributes();
            log.info("Attributes: {}", attributes);
            session.close();

            log.info("start session 2");
            session = sessionFactory.openSession();
            derivation = session.get(Derivation.class, derivationId);
            for (Attribute attribute : derivation.getAttributes()) {
                log.info("Attributes: {}", attribute);
            }
            session.close();
        }
    }

    private static Long insertDerivation(SessionFactory sessionFactory) {
        try (MDC.MDCCloseable ignored = MDC.putCloseable(MDC_STEP, "insert")){
            Session em = sessionFactory.openSession();

            // model
            Derivation derivation = Derivation.builder().name("Derivation 1").build();
            derivation.add(Attribute.builder().name("Attribute 1").build());
            derivation.add(Attribute.builder().name("Attribute 2").build());

            em.getTransaction().begin();
            em.persist(derivation);
            em.getTransaction().commit();
            em.clear();

            em.close();

            return derivation.getId();
        }
    }
}
