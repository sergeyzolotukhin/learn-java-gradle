package ua.in.sz.hibernate.xml;

import com.google.common.base.Stopwatch;
import lombok.experimental.UtilityClass;
import oracle.jdbc.OracleTypes;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.jdbc.Work;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.procedure.ProcedureOutputs;

import javax.persistence.ParameterMode;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
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


    @SuppressWarnings("SqlResolve")
    public static Long startSqlMonitor(StatelessSession session, String name) {
        return session.doReturningWork(connection -> {
            CallableStatement stmt = connection.prepareCall(
                    "{ ? = call dbms_sql_monitor.begin_operation ( " +
                            "dbop_name => ?, forced_tracking => ? ) " +
                            " }");
            stmt.registerOutParameter(1, OracleTypes.NUMBER);
            stmt.setString(2, name);
            stmt.setString(3, "Y");
            stmt.execute();
            return stmt.getLong(1);
        });
    }

    @SuppressWarnings("SqlResolve")
    public static void endSqlMonitor(StatelessSession session, String name, Long operationId) {
        session.doWork(connection -> {
            CallableStatement stmt = connection.prepareCall(
                    "call dbms_sql_monitor.end_operation ( dbop_name => ?, dbop_eid => ? ) ");
            stmt.setString(1, name);
            stmt.setLong(2, operationId);
            stmt.execute();
        });
    }
}
