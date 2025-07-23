package ua.in.sz.hibernate.interceptor;

import jakarta.persistence.RollbackException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ua.in.sz.hibernate.interceptor.entities.Determinant;
import ua.in.sz.hibernate.interceptor.interceptor.TransactionInterceptor;

@Slf4j
public class InterceptorMain {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .build();
        Metadata metadata = new MetadataSources(registry)
                .addAnnotatedClass(Determinant.class)
                .buildMetadata();

        SessionFactoryBuilder sessionFactoryBuilder = metadata.getSessionFactoryBuilder()
                .applyInterceptor(new TransactionInterceptor());
        try (
                SessionFactory sessionFactory = sessionFactoryBuilder.build()
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
            log.info("queries: {}", determinant);

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
        } catch (RollbackException e) {
            log.error("Error: ", e);
        }
    }
}
