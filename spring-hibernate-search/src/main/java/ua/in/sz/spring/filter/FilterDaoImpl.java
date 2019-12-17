package ua.in.sz.spring.filter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class FilterDaoImpl implements FilterDao {

    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E, D> List<D> search(Filter<E, D> filter) {
        return filter.getSearchCriteria(getSession()).list();
    }

    @Override
    public <E, D> Long count(Filter<E, D> filter) {
        return (Long) filter.getCountCriteria(getSession()).uniqueResult();
    }
}
