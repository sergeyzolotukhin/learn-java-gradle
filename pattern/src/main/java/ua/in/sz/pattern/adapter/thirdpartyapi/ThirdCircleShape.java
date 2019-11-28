package ua.in.sz.pattern.adapter.thirdpartyapi;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThirdCircleShape implements GeometricShape {
	@Override
	public String draw() {
		return getClass().getSimpleName();
	}
}
