package ua.in.sz.hibernate.cache.events;

import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.initialization.qual.Initialized;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.UnknownKeyFor;
import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.jpa.boot.spi.IntegratorProvider;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

import java.util.List;

/**
 * https://vladmihalcea.com/hibernate-event-listeners/
 * https://vladmihalcea.com/dto-projection-jpa-query/
 * https://medium.com/@adikale123/listening-and-intercepting-events-hibernate-aaccd04ab451
 */
@Slf4j
public class LogInsertIntegratorProvider implements IntegratorProvider {
    @Override
    public List<Integrator> getIntegrators() {
        return List.of(new LogInsertIntegrator());
    }

    public static class LogInsertIntegrator implements Integrator {
        @Override
        public void integrate(@UnknownKeyFor @NonNull @Initialized Metadata metadata, @UnknownKeyFor @NonNull @Initialized SessionFactoryImplementor sessionFactory, @UnknownKeyFor @NonNull @Initialized SessionFactoryServiceRegistry serviceRegistry) {
            EventListenerRegistry eventListenerRegistry = serviceRegistry.getService(EventListenerRegistry.class);
            eventListenerRegistry.appendListeners(EventType.POST_INSERT, LogInsertEventListener.INSTANCE);
        }

        @Override
        public void disintegrate(@UnknownKeyFor @NonNull @Initialized SessionFactoryImplementor sessionFactory, @UnknownKeyFor @NonNull @Initialized SessionFactoryServiceRegistry serviceRegistry) {

        }
    }
}
