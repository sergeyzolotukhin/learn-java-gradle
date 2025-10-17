package ua.in.sz.hibernate.merge;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ua.in.sz.hibernate.merge.entities.Determinant;
import ua.in.sz.hibernate.merge.entities.Group;

import java.util.Objects;

@Slf4j
public class MargeMain {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().build();
        try (
                SessionFactory sessionFactory = new MetadataSources(registry)
                        .addAnnotatedClass(Group.class)
                        .addAnnotatedClass(Determinant.class)
                        .buildMetadata()
                        .buildSessionFactory()
        ) {
            insertGroups(sessionFactory);
            mergeGroups(sessionFactory);
            queryGroup(sessionFactory);
        } catch (Exception e) {
            log.error("Error: ", e);
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    private static void queryGroup(SessionFactory sessionFactory) {
        Session s = sessionFactory.openSession();
        s.getTransaction().begin();

        Group group = s.byNaturalId(Group.class)
                .using(Group.Fields.name, "GR_D")
                .load();
        log.info("\nGroup: {} \n\tchildren: {}\n\tdeterminants: {}",
                group, group.getChildren(), group.getDeterminants());

        Determinant determinant = s.byNaturalId(Determinant.class)
                .using(Determinant.Fields.name, "DET_W")
                .load();
        log.info("\nDeterminant: {} \n\tgroups: {}",
                determinant, determinant.getParentGroups().stream().map(Group::getName).toList());

        s.getTransaction().commit();
        s.close();
    }

    private static void mergeGroups(SessionFactory sessionFactory) {
        Session em = sessionFactory.openSession();

        // model
        Group e = Group.builder().name("GR_E").build();

        Group t = em.byNaturalId(Group.class)
                .using(Group.Fields.name, "GR_D")
                .load();

        Group d = Group.builder()
                .id(t.getId())
                .name(t.getName())
                .withChild(e)
                .build();

        em.getTransaction().begin();

        em.persist(e);
        em.merge(d);

        log.info("merged");
        em.getTransaction().commit();
        em.clear();

        em.close();

        log.info("commited");
    }

    private static void insertGroups(SessionFactory sessionFactory) {
        Session em = sessionFactory.openSession();

        Group a = Group.builder().name("GR_A").description("inserted").build();
        Group b = Group.builder().name("GR_B").description("inserted").build();
        Group c = Group.builder().name("GR_C").description("inserted").build();

        Group d = Group.builder().name("GR_D").description("inserted")
                .withChild(a)
                .withChild(b)
                .withParent(c)
//                .withDeterminant(w)
                .build();

        Determinant w = Determinant.builder()
                .name("DET_W")
//                .withGroup(d)
                .build();

//        d.addDeterminant(w);
        w.addParentGroup(d);

        em.getTransaction().begin();

        em.persist(w);

        em.persist(a);
        em.persist(b);
        em.persist(c);
        em.persist(d);

        log.info("persisted");
        em.getTransaction().commit();
        em.clear();

        em.close();

        log.info("commited");
    }
}
