package ua.in.sz.predicate.log;

import org.slf4j.Logger;
import ua.in.sz.predicate.log.NamedPredicate;

import java.util.function.Predicate;

public class TracedPredicate<T> implements Predicate<T> {
	private final NamedPredicate<T> predicate;
	private final Logger log;

	public TracedPredicate(NamedPredicate<T> predicate, Logger log) {
		this.predicate = predicate;
		this.log = log;
	}

	@Override
	public boolean test(T t) {
		boolean match = predicate.test(t);

		if (match) {
			log.debug("Predicate '{}' is match at [{}]", predicate, t);
		} else {
			log.trace("Predicate '{}' is not match at [{}]", predicate, t);
		}

		return match;
	}

	@Override
	public String toString() {
		return predicate.toString();
	}
}
