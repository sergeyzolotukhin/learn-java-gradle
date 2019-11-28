package ua.in.sz.pattern.adapter.adapter;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.pattern.adapter.ownapi.Shape;
import ua.in.sz.pattern.adapter.thirdpartyapi.GeometricShape;

import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.joining;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Slf4j
public class CommonShapeAdapter implements Shape, GeometricShape {
	private GeometricShape geometryShapeDelegate;
	private Shape shapeDelegate;

	public CommonShapeAdapter(Shape shapeDelegate) {
		this.shapeDelegate = shapeDelegate;
	}

	public CommonShapeAdapter(GeometricShape geometryShapeDelegate) {
		this.geometryShapeDelegate = geometryShapeDelegate;
	}

	@Override
	public String render() {
		return doDraw();
	}

	@Override
	public String draw() {
		return doDraw();
	}

	private String doDraw() {
		return Stream.of(
				ofNullable(shapeDelegate).map(Shape::render),
				ofNullable(geometryShapeDelegate).map(GeometricShape::draw)
		)
				.filter(Optional::isPresent)
				.map(e -> e.orElse(EMPTY))
				.collect(joining(","));
	}
}
