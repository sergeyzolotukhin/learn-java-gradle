package ua.in.sz.pattern.adapter.ownapi;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Builder
public class Drawing {
	@Builder.Default
	private List<Shape> shapes = new ArrayList<>();

	public void render() {
		String result = shapes.stream().map(Shape::render).collect(Collectors.joining(","));

		log.info("drawing: {}", result);
	}
}
