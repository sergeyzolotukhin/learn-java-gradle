package ua.in.sz.predicate;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.predicate.log.NamedPredicate;
import ua.in.sz.predicate.model.Rect;

import java.util.List;

import static ua.in.sz.predicate.log.NamedPredicate.named;
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

		NamedPredicate<Rect> predicate = named(evenWidth().and(evenHeight()), "width and height is even");
		log.info("rects filter by {}", predicate);

		rects.stream()
				.filter(traced(predicate, log))
				.forEach(r -> log.info("Rect: {}", r));
	}
}
