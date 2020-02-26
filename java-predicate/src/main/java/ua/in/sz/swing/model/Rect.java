package ua.in.sz.swing.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@AllArgsConstructor(staticName = "of")
public class Rect {
	private int x;
	private int y;
	private int width;
	private int height;
}
