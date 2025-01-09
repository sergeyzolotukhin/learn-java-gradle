package ua.in.sz.hibernate.identifiers.assigned;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ua.in.sz.hibernate.identifiers.assigned.entities.CompositeAssignedDerivation;

@Slf4j
public class CompositeAssignedMain {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().build();
        try (
                SessionFactory sessionFactory = new MetadataSources(registry)
                        .addAnnotatedClass(CompositeAssignedDerivation.class)
                        .buildMetadata()
                        .buildSessionFactory()
        ) {
            insertDerivation(sessionFactory);
        } catch (Exception e) {
            log.error("Error: ", e);
            // The registry would be destroyed by the SessionFactory, but we
            // had trouble building the SessionFactory so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);

        }
    }

    private static void insertDerivation(SessionFactory sessionFactory) {
        Session em = sessionFactory.openSession();

        em.getTransaction().begin();

        for (int i = 0; i < 20; i++) {
            CompositeAssignedDerivation derivation = CompositeAssignedDerivation.builder()
                    .id(2L)
                    .name(String.format("Derivation %s", i))
                    .build();
            em.persist(derivation);
            log.info("persist derivation : {} / {}", derivation.getId(), derivation.getSubId());
        }

        em.getTransaction().commit();
        em.clear();
    }
}
