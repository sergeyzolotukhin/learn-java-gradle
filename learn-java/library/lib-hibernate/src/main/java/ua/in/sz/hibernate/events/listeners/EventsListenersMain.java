package ua.in.sz.hibernate.events.listeners;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.engine.spi.PersistenceContext;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventSource;
import org.hibernate.event.spi.EventType;
import org.hibernate.event.spi.FlushEvent;
import org.hibernate.event.spi.FlushEventListener;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.query.Query;
import ua.in.sz.hibernate.cascade.entities.Configuration;
import ua.in.sz.hibernate.cascade.entities.Definition;
import ua.in.sz.hibernate.cascade.entities.Dependency;
import ua.in.sz.hibernate.cascade.entities.Parameter;
import ua.in.sz.hibernate.events.listeners.listeners.OnChangeDerivationStatusPostUpdateEventListener;

import java.util.List;
import java.util.Objects;

@Slf4j
public class EventsListenersMain {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .build();
        try (
                SessionFactory sessionFactory = new MetadataSources(registry)
                        .addAnnotatedClass(Parameter.class)
                        .addAnnotatedClass(Configuration.class)
                        .addAnnotatedClass(Dependency.class)
                        .addAnnotatedClass(Definition.class)
                        .buildMetadata()
                        .buildSessionFactory()
        ) {
            eventPostUpdateEventListener((SessionFactoryImpl) sessionFactory);

            Long derivationId = insertDerivation(sessionFactory);

            Session s1 = sessionFactory.openSession();
            s1.getTransaction().begin();
            Definition dep = s1.get(Definition.class, derivationId);
            log.info("Dep Step 1: {}", dep);
            dep.setName("Other name 1");
//            dep.setDescription("Other name 1");
            dep.getDependencies().clear();
            s1.getTransaction().commit();
            s1.clear();
            s1.close();

            log.info("Session closed");
        } catch (Exception e) {
            log.error("Error: ", e);
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    private static void eventPostUpdateEventListener(SessionFactoryImpl sessionFactory) {
        sessionFactory.getEventEngine()
                .getListenerRegistry()
                .getEventListenerGroup(EventType.POST_UPDATE)
                .appendListener(new OnChangeDerivationStatusPostUpdateEventListener());
    }

    private static Long insertDerivation(SessionFactory sessionFactory) {
        log.info("insert");
        Session em = sessionFactory.openSession();

        // model
        Definition definition = Definition.builder().name("definition 1").description("Desc A")
                .dependency(Dependency.builder().name("Dependency A")
                        .configuration(Configuration.builder().name("Configuration A")
                                .parameter(Parameter.builder().name("Parameter A").build())
                                .parameter(Parameter.builder().name("Parameter B").build())
                                .build())
                        .configuration(Configuration.builder().name("Configuration B").build())
                        .build())
                .dependency(Dependency.builder().name("Dependency A").build())
                .build();

        em.getTransaction().begin();
        em.persist(definition);
        em.getTransaction().commit();
        em.clear();

        em.close();

        Long id = definition.getId();

        log.info("ID: {}", id);

        return id;
    }
}
