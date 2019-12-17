package ua.in.sz.spring.filter;

import org.hibernate.Criteria;
import org.hibernate.Session;

public interface Filter<E, D> {

    Criteria searchCriteria(Session session);

    Criteria countCriteria(Session session);

}
