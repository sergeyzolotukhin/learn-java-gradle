package ua.in.sz.pattern.adapter.thirdpartyapi;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Builder
public class DrawingShape {
	@Builder.Default
	private List<GeometricShape> shapes = new ArrayList<>();

	public void draw() {
		String result = shapes.stream().map(GeometricShape::draw).collect(Collectors.joining(","));

		log.info("drawing: {}", result);
	}
}
