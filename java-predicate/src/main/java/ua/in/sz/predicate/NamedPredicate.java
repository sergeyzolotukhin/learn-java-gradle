package ua.in.sz.predicate;

import java.util.function.Predicate;

public class NamedPredicate<T> implements Predicate<T> {

	private final Predicate<T> predicate;
	private final String name;

	public NamedPredicate(Predicate<T> predicate, String name) {
		this.predicate = predicate;
		this.name = name;
	}

	@Override
	public boolean test(T t) {
		return predicate.test(t);
	}

	@Override
	public String toString() {
		return name;
	}
}
