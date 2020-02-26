package ua.in.sz.swing;

import com.google.common.base.Predicate;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.Nullable;
import ua.in.sz.swing.model.Rect;


@Slf4j
@UtilityClass
public class GuavaPredicates {
	public static Predicate<Rect> evenWidth() {
		return new Predicate<Rect>() {
			@Override
			public boolean apply(@Nullable Rect rect) {
				return rect.getWidth() % 2 == 0;
			}

			@Override
			public String toString() {
				return "rect width is even";
			}
		};
	}

	public static Predicate<Rect> evenHeight() {
		return new Predicate<Rect>() {
			@Override
			public boolean apply(@Nullable Rect rect) {
				return rect.getHeight() % 2 == 0;
			}

			@Override
			public String toString() {
				return "rect height is even";
			}
		};
	}
}
