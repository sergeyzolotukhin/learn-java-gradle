package ua.in.sz.swing;

import java.util.function.Predicate;

public interface NamedPredicate<T> extends Predicate<T> {
	String name();
}
