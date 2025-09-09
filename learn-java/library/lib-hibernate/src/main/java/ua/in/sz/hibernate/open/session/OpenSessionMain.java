package ua.in.sz.hibernate.open.session;

import jakarta.persistence.RollbackException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ua.in.sz.hibernate.open.session.entities.Determinant;

@Slf4j
public class OpenSessionMain {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().build();
        try (
                SessionFactory sessionFactory = new MetadataSources(registry)
                        .addAnnotatedClass(Determinant.class)
                        .buildMetadata()
                        .buildSessionFactory()
        ) {
            insertDeterminants(sessionFactory);
            queryDeterminants(sessionFactory);
        } catch (Exception e) {
            log.error("Error: ", e);
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    private static void queryDeterminants(SessionFactory sessionFactory) {
        try (Session s = sessionFactory.openSession()) {
            s.getTransaction().begin();

            Determinant determinant = s.byNaturalId(Determinant.class)
                    .using(Determinant.Fields.name, "DET_W")
                    .load();
            log.info("queries\nDeterminant: {}", determinant);

            Determinant w = Determinant.builder()
               //     .id(61L)
                    .id(determinant.getId())
                    .name("DET_W")
                    .build();
//            s.saveOrUpdate(w);
//            s.merge(w);

            s.getTransaction().commit();
        } catch (RollbackException e) {
            log.error("Error: ", e);
        }
    }

    private static void insertDeterminants(SessionFactory sessionFactory) {
        try (Session s = sessionFactory.openSession()) {

            Determinant w = Determinant.builder()
                    .name("DET_W")
                    .build();

            s.getTransaction().begin();

            s.persist(w);

            log.info("persisted");
            s.getTransaction().commit();

            log.info("commited");
        } catch (RollbackException e) {
            log.error("Error: ", e);
        }
    }
}
