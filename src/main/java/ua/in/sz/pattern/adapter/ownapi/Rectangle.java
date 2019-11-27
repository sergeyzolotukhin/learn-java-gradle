package ua.in.sz.pattern.adapter.ownapi;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Rectangle implements Shape {
	@Override
	public void render() {
		log.info("{}", getClass().getSimpleName());
	}
}
