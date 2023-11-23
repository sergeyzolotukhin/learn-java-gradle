package ua.in.sz.spring.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.stream.Stream;

public abstract class AbstractFilterDao implements FilterDao {

    protected Logger log = LoggerFactory.getLogger(getClass());

    public abstract EntityManager getEntityManager();

    @Override
    public <E, D> Stream<D> search(Filter<E, D> filter) {
        CriteriaQuery<D> query = filter.searchQuery(getEntityManager().getCriteriaBuilder());
        return getEntityManager().createQuery(query).getResultStream();
    }

    @Override
    public <E, D> Long count(Filter<E, D> filter) {
        CriteriaQuery<Long> query = filter.countQuery(getEntityManager().getCriteriaBuilder());
        return getEntityManager().createQuery(query).getSingleResult();
    }
}
