package ua.in.sz.spring.export;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.in.sz.spring.filter.Filter;
import ua.in.sz.spring.filter.FilterDao;

import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
@Repository
public class ScheduleDaoImpl implements FilterDao {

    private final EntityManager entityManager;

    private SessionFactory sessionFactory;

    @Autowired
    public ScheduleDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

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
