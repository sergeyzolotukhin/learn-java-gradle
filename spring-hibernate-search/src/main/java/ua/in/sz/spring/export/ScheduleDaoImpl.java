package ua.in.sz.spring.export;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.stream.Stream;

@Slf4j
@Repository
public class ScheduleDaoImpl implements FilterDao {

    private final EntityManager entityManager;

    @Autowired
    public ScheduleDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public <E, D> Stream<D> search(Filter<E, D> filter) {
        CriteriaQuery<D> query = filter.searchQuery(entityManager.getCriteriaBuilder());
        return entityManager.createQuery(query).getResultStream();
    }

    @Override
    public <E, D> Long count(Filter<E, D> filter) {
        CriteriaQuery<Long> query = filter.countQuery(entityManager.getCriteriaBuilder());
        return entityManager.createQuery(query).getSingleResult();
    }
}
