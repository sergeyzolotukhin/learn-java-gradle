package ua.in.sz.pattern.adapter.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.pattern.adapter.ownapi.Shape;
import ua.in.sz.pattern.adapter.thirdpartyapi.GeometricShape;

@Slf4j
@RequiredArgsConstructor
public class ShapeAdapter implements Shape {
	private final GeometricShape delegate;

	@Override
	public void render() {
		delegate.draw();
	}
}
