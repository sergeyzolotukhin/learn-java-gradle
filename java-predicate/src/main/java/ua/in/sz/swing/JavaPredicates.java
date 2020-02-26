package ua.in.sz.swing;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.swing.model.Rect;

import java.util.function.Predicate;

@Slf4j
@UtilityClass
public class JavaPredicates {
	public static Predicate<Rect> evenWidth() {
		return rect -> rect.getWidth() % 2 == 0;
	}

	public static Predicate<Rect> evenHeight() {
		return rect -> rect.getHeight() % 2 == 0;
	}
}
