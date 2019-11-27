package ua.in.sz.pattern.adapter.ownapi;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

@Slf4j
@Builder
public class Drawing {
	@Builder.Default
	private List<Shape> shapes = new ArrayList<>();

	public void render() {
		String result = shapes.stream().map(Shape::render).collect(joining(","));

		log.info("drawing: {}", result);
	}
}
