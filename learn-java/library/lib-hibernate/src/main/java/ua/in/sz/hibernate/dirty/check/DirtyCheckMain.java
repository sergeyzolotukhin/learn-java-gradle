package ua.in.sz.hibernate.dirty.check;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ua.in.sz.hibernate.dirty.check.model.DirtyParameter;

@Slf4j
public class DirtyCheckMain {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().build();
        try (
                SessionFactory sessionFactory = new MetadataSources(registry)
                        .addAnnotatedClass(DirtyParameter.class)
                        .buildMetadata()
                        .buildSessionFactory()
        ) {
            log.info("inserting ...");
            Long paramId = insertModel(sessionFactory);

            log.info("loading ...");
            Session s1 = sessionFactory.openSession();
            s1.getTransaction().begin();
            DirtyParameter parm = s1.get(DirtyParameter.class, paramId);
            log.info("flushing ...");
            parm.setName("Other Name");
            s1.flush();
            log.info("commiting ...");
            s1.getTransaction().commit();
            s1.clear();
            s1.close();

            log.info("Session closed");
        } catch (Exception e) {
            log.error("Error: ", e);
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    private static Long insertModel(SessionFactory sessionFactory) {
        // model
        DirtyParameter parameterA = DirtyParameter.builder().name("Parameter A").build();

        // persist
        Session em = sessionFactory.openSession();

        em.getTransaction().begin();
        em.persist(parameterA);
        em.getTransaction().commit();
        em.clear();

        em.close();

        return parameterA.getId();
    }
}
