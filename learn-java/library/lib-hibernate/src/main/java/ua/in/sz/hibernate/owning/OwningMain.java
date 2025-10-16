package ua.in.sz.hibernate.owning;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ua.in.sz.hibernate.owning.entities.Customer;
import ua.in.sz.hibernate.owning.entities.Order;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
public class OwningMain {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().build();
        try (
                SessionFactory sessionFactory = new MetadataSources(registry)
                        .addAnnotatedClass(Order.class)
                        .addAnnotatedClass(Customer.class)
                        .buildMetadata()
                        .buildSessionFactory()
        ) {
            log.info("create customer");
            Long cId1 = runInSession(sessionFactory, (s) -> {
                Customer c = Customer.builder().build();
                s.persist(c);
                return c.getId();
            });

            log.info("create customer");
            Long oId1 = runInSession(sessionFactory, (s) -> {
                Order o = Order.builder().build();
                s.persist(o);
                return o.getId();
            });

            log.info("link order to consumer");
            runInSession(sessionFactory, (s) -> {
                Order o = s.get(Order.class, oId1);
                Customer c = s.get(Customer.class, cId1);

                o.setCustomer(c);

                s.persist(o);
                return o.getId();
            });

            log.info("read order");
            runInSession(sessionFactory, (s) -> {
                Query<Order> q = s.createQuery("FROM Order", Order.class);
                List<Order> r = q.list();
                log.info("Dep Step 3: {}", r);
            });

            log.info("Session factory closed");
        } catch (Exception e) {
            log.error("Error: ", e);
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    private static void runInSession(SessionFactory sessionFactory, Consumer<Session> consumer) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        consumer.accept(session);

        session.getTransaction().commit();
        session.close();
    }

    private static <R> R runInSession(SessionFactory sessionFactory, Function<Session, R> function) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        R r = function.apply(session);

        session.getTransaction().commit();
        session.close();

        return r;
    }
}
