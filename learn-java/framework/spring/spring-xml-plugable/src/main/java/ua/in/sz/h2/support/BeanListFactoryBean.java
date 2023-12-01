package ua.in.sz.h2.support;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BeanListFactoryBean<T> extends AbstractTypeAwareSupport<T> implements FactoryBean<List<T>> {

	private static final Comparator<Object> COMPARATOR = new AnnotationAwareOrderComparator();

	@NonNull
	public List<T> getObject() {
		List<T> beans = new ArrayList<T>();
		beans.addAll(getBeans());
		Collections.sort(beans, COMPARATOR);

		return beans;
	}

	@NonNull
	public Class<?> getObjectType() {
		return List.class;
	}

	public boolean isSingleton() {
		return true;
	}
}
