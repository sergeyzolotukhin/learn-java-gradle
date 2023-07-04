package ua.in.sz.pattern.adapter.ownapi;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OwnRectangle implements Shape {
	@Override
	public String render() {
		return getClass().getSimpleName();
	}
}
