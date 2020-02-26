package ua.in.sz.swing;

import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	public static <T> Predicate<T> log(NamedPredicate<T> predicate, Class clazz) {
		Logger log = LoggerFactory.getLogger(clazz.getName());

		return rect -> {
			boolean match = predicate.test(rect);

			if (match) {
				log.debug("Predicate '{}' is match at [{}]", predicate, rect);
			} else {
				log.trace("Predicate '{}' is not match at [{}]", predicate, rect);
			}

			return match;
		};
	}

	public static <T> NamedPredicate<T> named(Predicate<T> predicate, String name) {
		return new NamedPredicate<T>() {
			@Override
			public boolean test(T rect) {
				return predicate.test(rect);
			}

			@Override
			public String name() {
				return name;
			}

			@Override
			public String toString() {
				return name();
			}
		};
	}
}
