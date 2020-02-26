package ua.in.sz.predicate.log;

import org.slf4j.Logger;

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
		if (predicate.test(t)) {
			logMatched(predicate, t);
			return true;
		} else {
			logNotMatched(predicate, t);
			return false;
		}
	}

	@Override
	public String toString() {
		return predicate.toString();
	}

	protected void logNotMatched(NamedPredicate<T> predicate, T t) {
		log.trace("Predicate '{}' is not match at [{}]", predicate, t);
	}

	protected void logMatched(NamedPredicate<T> predicate, T t) {
		log.debug("Predicate '{}' is match at [{}]", predicate, t);
	}

	public static <T> Predicate<T> traced(NamedPredicate<T> predicate, Logger log) {
		return new TracedPredicate<>(predicate, log);
	}
}
