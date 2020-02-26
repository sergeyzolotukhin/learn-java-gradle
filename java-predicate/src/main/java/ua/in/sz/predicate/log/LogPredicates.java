package ua.in.sz.predicate.log;

import lombok.experimental.UtilityClass;
import org.slf4j.Logger;

import java.util.function.Predicate;

public class LogPredicates {

	public static <T> Predicate<T> traced(Logger log, String name, Predicate<T> predicate) {
		return traced(log, named(name, predicate));
	}

	public static <T> Predicate<T> traced(Predicate<T> predicate, Logger log, String name) {
		return traced(log, named(name, predicate));
	}

	public static <T> Predicate<T> traced(Predicate<T> predicate, String name, Logger log) {
		return traced(log, named(name, predicate));
	}

	public static <T> TracedPredicate<T> traced(Logger log, NamedPredicate<T> predicate) {
		return new TracedPredicate<>(predicate, log);
	}

	public static <T> NamedPredicate<T> named(String name, Predicate<T> predicate) {
		return new NamedPredicate<>(predicate, name);
	}
}
