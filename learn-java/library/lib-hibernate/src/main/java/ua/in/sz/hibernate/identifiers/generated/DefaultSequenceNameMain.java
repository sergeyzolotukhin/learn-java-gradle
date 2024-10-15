package ua.in.sz.hibernate.identifiers.generated;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ua.in.sz.hibernate.identifiers.generated.entities.DefaultSequenceNameDerivation;
import ua.in.sz.hibernate.identifiers.generated.entities.DefaultSequenceNameSecondDerivation;

@Slf4j
public class DefaultSequenceNameMain {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().build();
        try (
                SessionFactory sessionFactory = new MetadataSources(registry)
                        .addAnnotatedClass(DefaultSequenceNameDerivation.class)
                        .addAnnotatedClass(DefaultSequenceNameSecondDerivation.class)
                        .addPackage("ua.in.sz.hibernate.identifiers.generated.entities")
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
            DefaultSequenceNameDerivation derivation = DefaultSequenceNameDerivation.builder()
                    .name(String.format("Derivation %s", i))
                    .build();
            em.persist(derivation);

            DefaultSequenceNameSecondDerivation secondDerivation = DefaultSequenceNameSecondDerivation.builder()
                    .name(String.format("Derivation %s", i))
                    .build();
            em.persist(secondDerivation);
        }

        em.getTransaction().rollback();
        em.clear();
    }
}
