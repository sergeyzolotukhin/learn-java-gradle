package ua.in.sz.hibernate.second.level.cache;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cache.jcache.internal.JCacheRegionFactory;
import org.hibernate.cache.spi.CacheImplementor;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.MDC;
import ua.in.sz.hibernate.second.level.cache.entities.Attribute;
import ua.in.sz.hibernate.second.level.cache.entities.Derivation;

import javax.cache.Cache;
import javax.cache.CacheManager;
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
            sessionFactory.getCache().evictAllRegions();

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
//            dump(sessionFactory.getCache());

            {
                log.info("start session 1");
                Session session = sessionFactory.openSession();
                Derivation derivation = session.get(Derivation.class, derivationId);
                Set<Attribute> attributes = derivation.getAttributes();
                log.info("Attributes: {}", attributes.stream().count());
                session.close();
            }

            {
                log.info("start session 2");
                Session session = sessionFactory.openSession();
                Derivation derivation = session.get(Derivation.class, derivationId);
                Set<Attribute> attributes = derivation.getAttributes();
                log.info("Attributes: {}", attributes.stream().count());
                session.close();
            }

            {
                log.info("start session 3");
                Session session = sessionFactory.openSession();
                Derivation derivation = session.get(Derivation.class, derivationId);
                Set<Attribute> attributes = derivation.getAttributes();
                log.info("Attributes: {}", attributes.stream().count());
                session.close();
            }

//            dump(sessionFactory.getCache());
        }
    }

    private static void dump(org.hibernate.Cache cache1) {
        CacheImplementor cacheImplementor = cache1.unwrap(CacheImplementor.class);
        JCacheRegionFactory regionFactory = (JCacheRegionFactory)cacheImplementor.getRegionFactory();
        CacheManager cacheManager = regionFactory.getCacheManager();

        for (String regionName : cacheImplementor.getCacheRegionNames()) {
            log.info("Cache region: {}", regionName);

            Cache<Object, Object> c = cacheManager.getCache(regionName);
            for (Cache.Entry<Object, Object> e : c) {
                log.info("{}: {}", e.getKey(), e.getValue());
            }
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
