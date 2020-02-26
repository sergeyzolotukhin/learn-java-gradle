package ua.in.sz.predicate;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.predicate.model.Rect;

import java.util.List;
import java.util.function.Predicate;

import static ua.in.sz.predicate.model.RectPredicates.evenHeight;
import static ua.in.sz.predicate.model.RectPredicates.evenWidth;
import static ua.in.sz.predicate.log.TracedPredicate.traced;


@Slf4j
public class Application {
	public static void main(String[] args) {
		List<Rect> rects = List.of(
				Rect.builder().width(1).height(1).build(),
				Rect.builder().width(2).height(1).build(),
				Rect.builder().width(3).height(2).build(),
				Rect.builder().width(4).height(2).build()
		);

		Predicate<Rect> predicate = traced(evenWidth().and(evenHeight()), "width and height is even", log);

		log.info("rect filter by {}", predicate);

		rects.stream()
				.filter(predicate)
				.forEach(r -> log.info("Rect: {}", r));
	}
}
