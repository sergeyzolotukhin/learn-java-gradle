package ua.in.sz.spring.export;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ua.in.sz.spring.filter.Filter;
import ua.in.sz.spring.filter.FilterDao;

import java.util.List;

@Repository
public class ScheduleDaoImpl implements FilterDao {

    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E, D> List<D> search(Filter<E, D> filter) {
        return filter.searchCriteria(getSession()).list();
    }

    @Override
    public <E, D> Long count(Filter<E, D> filter) {
        return (Long) filter.countCriteria(getSession()).uniqueResult();
    }
}
