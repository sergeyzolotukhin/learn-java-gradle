package ua.in.sz.hibernate.cascade;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
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

            Session s1 = sessionFactory.openSession();
            s1.getTransaction().begin();
            Definition dep = s1.get(Definition.class, derivationId);
            log.info("Dep Step 1: {}", dep);
            dep.getDependencies().clear();
            s1.getTransaction().commit();
            s1.clear();
            s1.close();

            Session s2 = sessionFactory.openSession();
            Definition dep2 = s2.get(Definition.class, derivationId);
            log.info("Dep Step 2: {}", dep2);
            s2.close();

            Session s3 = sessionFactory.openSession();
            Query<Configuration> query = s3.createQuery("FROM Configuration", Configuration.class);
            List<Configuration> result = query.list();
            log.info("Dep Step 3: {}", result);
            s3.close();

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
