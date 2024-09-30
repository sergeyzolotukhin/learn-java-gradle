package ua.in.sz.hibernate.cascade;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ua.in.sz.hibernate.cascade.entities.Dependency;
import ua.in.sz.hibernate.cascade.entities.Configuration;
import ua.in.sz.hibernate.cascade.entities.Definition;

import java.util.List;
import java.util.Set;

@Slf4j
public class CascadeMain {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().build();
        try (
                SessionFactory sessionFactory = new MetadataSources(registry)
                        .addAnnotatedClass(Configuration.class)
                        .addAnnotatedClass(Dependency.class)
                        .addAnnotatedClass(Definition.class)
                        .buildMetadata()
                        .buildSessionFactory()
        ) {
            Long derivationId = insertDerivation(sessionFactory);

            log.info("Session is started");
            Session session = sessionFactory.openSession();
            Definition dep = session.get(Definition.class, derivationId);
            log.info("Dep: {}", dep);

            session.close();
            log.info("Session closed");
        } catch (Exception e) {
            log.error("Error: ", e);
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    private static Long insertDerivation(SessionFactory sessionFactory) {
        log.info("insert");
        Session em = sessionFactory.openSession();

        // model
        Configuration confA = Configuration.builder().name("Configuration A").build();
        Configuration confB = Configuration.builder().name("Configuration A").build();

        Dependency depA = Dependency.builder().name("Dependency A").build();
        depA.setConfigurations(List.of(confA, confB));
        Dependency depB = Dependency.builder().name("Dependency A").build();
        Definition definition = Definition.builder().name("definition 1").build();
        depA.setDefinition(definition);
        depB.setDefinition(definition);
        definition.setDependencies(Set.of(depA, depB));

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
