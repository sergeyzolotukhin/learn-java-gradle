package ua.in.sz.hibernate.xml;

import lombok.experimental.UtilityClass;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;

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
}
