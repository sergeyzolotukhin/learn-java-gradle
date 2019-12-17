package ua.in.sz.spring.filter;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public abstract class Filter<E, D> {

    protected Conjunction and = Restrictions.conjunction();
    protected Disjunction or = Restrictions.disjunction();

    private List<Order> order = new LinkedList<>();

    private int offset = 1;
    private int pageSize = 1;

    public abstract Class<E> entityClass();

    public abstract Class<D> dtoClass();

    protected abstract Projection projection();

    protected abstract void aliases(Criteria criteria);

    public abstract Criterion criterion();

    public List<Order> order() {
        return order;
    }

    public final Criteria searchCriteria(Session session) {
        Criteria c = session.createCriteria(entityClass());
        aliases(c);

        Criteria criteria = buildCriteria(
                c,
                criterion(),
                order(),
                offset(), limit());

        Optional.ofNullable(projection()).ifPresent(criteria::setProjection);

        criteria.setResultTransformer(Transformers.aliasToBean(dtoClass()));

        return criteria;

    }

    public final Criteria countCriteria(Session session) {
        Criteria c = session.createCriteria(entityClass());
        aliases(c);

        c.setProjection(Projections.countDistinct("id"));

        buildCriteria(c, criterion(), null, null, null);

        return c;
    }

    // ================================================================================================================
    //
    // ================================================================================================================

    public void and(Criterion criterion) {
        and.add(criterion);
    }


    public void or(Criterion criterion) {
        or.add(criterion);
    }

    public int offset() {
        return limit() * (getOffset() - 1);
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = Math.max(offset, 1);
    }

    public int limit() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = Math.max(pageSize, 1);
    }

    // ================================================================================================================
    // private methods
    // ================================================================================================================

    public static Criteria buildCriteria(
            Criteria criteria, Criterion criterion,
            List<Order> orderByList, Integer offset, Integer limit
    ) {
        if (criterion != null) {
            criteria.add(criterion);
        }

        if (orderByList != null) {
            for (Order orderBy : orderByList) {
                criteria.addOrder(orderBy);
            }
        }

        if (offset != null && limit != null) {
            criteria.setFirstResult(offset);
            criteria.setMaxResults(limit);
        }

        return criteria;
    }
}
