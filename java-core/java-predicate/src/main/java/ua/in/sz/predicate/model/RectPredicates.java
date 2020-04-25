package ua.in.sz.predicate.model;

import lombok.experimental.UtilityClass;

import java.util.function.Predicate;

@UtilityClass
public class RectPredicates {
	public static Predicate<Rect> evenWidth() {
		return rect -> rect.getWidth() % 2 == 0;
	}

	public static Predicate<Rect> evenHeight() {
		return rect -> rect.getHeight() % 2 == 0;
	}
}
