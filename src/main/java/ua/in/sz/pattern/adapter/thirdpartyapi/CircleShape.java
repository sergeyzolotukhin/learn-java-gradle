package ua.in.sz.pattern.adapter.thirdpartyapi;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CircleShape implements GeometricShape {
	@Override
	public void draw() {
		log.info("{}", getClass().getSimpleName());
	}
}
