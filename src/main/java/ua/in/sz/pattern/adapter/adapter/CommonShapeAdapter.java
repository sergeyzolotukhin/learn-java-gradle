package ua.in.sz.pattern.adapter.adapter;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.pattern.adapter.ownapi.Shape;
import ua.in.sz.pattern.adapter.thirdpartyapi.GeometricShape;

import java.util.Optional;

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
	public void render() {
		doDraw();
	}

	@Override
	public void draw() {
		doDraw();
	}

	private void doDraw() {
		Optional.ofNullable(shapeDelegate).ifPresent(Shape::render);
		Optional.ofNullable(geometryShapeDelegate).ifPresent(GeometricShape::draw);
	}
}
