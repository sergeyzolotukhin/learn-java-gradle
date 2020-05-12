package ua.in.sz.hibernate.xml;

import lombok.experimental.UtilityClass;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

import java.sql.Connection;
import java.util.function.Consumer;
import java.util.function.Function;

@UtilityClass
public class Sessions {
    public static <R> R doInSession(SessionFactory sessionFactory, Function<Session, R> function) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            R result = function.apply(session);
            session.getTransaction().commit();

            return result;
        }
    }

    public static <R> R doInStatelessSession(SessionFactory sessionFactory, Function<StatelessSession, R> function) {
        try (StatelessSession session = sessionFactory.openStatelessSession()) {
            session.beginTransaction();
            R result = function.apply(session);
            session.getTransaction().commit();

            return result;
        }
    }

    public static void doInSessionFactory(Consumer<SessionFactory> function) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            MetadataSources metadataSources = new MetadataSources(registry);

            SessionFactory sessionFactory = metadataSources.buildMetadata().buildSessionFactory();

            function.accept(sessionFactory);

            sessionFactory.close();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static void doInConnection(ConnectionConsumer<Connection> consumer) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            MetadataSources metadataSources = new MetadataSources(registry);
            Connection con = metadataSources.getServiceRegistry().getService(ConnectionProvider.class).getConnection();

            consumer.accept(con);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public interface ConnectionConsumer<T> {
        void accept(T t) throws Exception;
    }
}
