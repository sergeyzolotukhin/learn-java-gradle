package ua.in.sz.spring.export;

import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractFilter<E, D> implements Filter<E, D> {

	protected Conjunction and = Restrictions.conjunction();
	protected Disjunction or = Restrictions.disjunction();

	private List<Order> order = new LinkedList<>();

	private int offset = 1;
	private int pageSize = 1;

	public abstract Class<E> entityClass();

	public abstract Class<D> dtoClass();

	protected abstract List<Selection<?>> selection(Root<E> from);

	@Override
	public CriteriaQuery<D> searchQuery(CriteriaBuilder cb) {
		CriteriaQuery<D> cq = cb.createQuery(dtoClass());

		Root<E> from = cq.from(entityClass());
		cq.select(cb.construct(dtoClass(), selection(from).toArray(new Selection<?>[0])));

		return cq;
	}

	@Override
	public final CriteriaQuery<Long> countQuery(CriteriaBuilder cb) {
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		return cq.select(cb.count(cq.from(entityClass())));
	}

	// ================================================================================================================
	//
	// ================================================================================================================

	protected void aliases(Criteria searchCriteria) {
	}

	public Criterion criterion() {
		return and.add(or);
	}

	public List<Order> order() {
		return order;
	}

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

	public static CriteriaQuery buildCriteria(
			CriteriaQuery criteria, Criterion criterion,
			List<Order> orderByList, Integer offset, Integer limit
	) {
       /* if (criterion != null) {
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
        }*/

		return criteria;
	}
}
