package ua.in.sz.bean.validation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor(staticName = "of")
public class Rect {
	private int x;
	private int y;
	private int width;
	private int height;
	private Sphera sphera;
}
