package ua.in.sz.spring.filter;

import java.util.List;

public interface FilterDao {
    <E,D> List<D> search(Filter<E, D> filter);

    <E,D> Long count(Filter<E, D> filter);
}
