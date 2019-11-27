package ua.in.sz.pattern.adapter.thirdpartyapi;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Builder
public class DrawingShape {
	@Builder.Default
	private List<GeometricShape> shapes = new ArrayList<>();

	public void draw() {
		log.info("start drawing");

		shapes.forEach(GeometricShape::draw);

		log.info("end drawing");
	}
}
