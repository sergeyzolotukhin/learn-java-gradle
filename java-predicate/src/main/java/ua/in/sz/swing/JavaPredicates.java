package ua.in.sz.swing;

import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import ua.in.sz.swing.model.Rect;

import java.util.function.Predicate;

@UtilityClass
public class JavaPredicates {
	public static Predicate<Rect> evenWidth() {
		return rect -> rect.getWidth() % 2 == 0;
	}

	public static Predicate<Rect> evenHeight() {
		return rect -> rect.getHeight() % 2 == 0;
	}

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
