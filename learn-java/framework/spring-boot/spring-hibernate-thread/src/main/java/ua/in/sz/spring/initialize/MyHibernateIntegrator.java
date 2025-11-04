package ua.in.sz.spring.initialize;

import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.spi.BootstrapContext;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.jpa.boot.spi.IntegratorProvider;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

import java.util.List;

@Slf4j
public class MyHibernateIntegrator implements Integrator, IntegratorProvider {

    @Override
    public void integrate(@NonNull Metadata metadata, @NonNull BootstrapContext bootstrapContext, @NonNull SessionFactoryImplementor sessionFactory) {
        log.info("integrate");
    }

    @Override
    public void disintegrate(@NonNull SessionFactoryImplementor sessionFactory,@NonNull SessionFactoryServiceRegistry serviceRegistry) {
        log.info("disintegrate");
    }

    @Override
    public List<Integrator> getIntegrators() {
        return List.of(new MyHibernateIntegrator());
    }
}