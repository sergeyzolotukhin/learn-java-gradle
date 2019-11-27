package ua.in.sz.pattern.adapter.ownapi;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Builder
public class Drawing {
	@Builder.Default
	private List<Shape> shapes = new ArrayList<>();

	public void render() {
		log.info("start drawing");

		shapes.forEach(Shape::render);

		log.info("end drawing");
	}
}
