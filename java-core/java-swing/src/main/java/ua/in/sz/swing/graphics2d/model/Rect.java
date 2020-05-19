package ua.in.sz.swing.graphics2d.model;

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

	public Rect intersection(Rect r) {
		int x1 = Math.max(x, r.x);
		int y1 = Math.max(y, r.y);

		int x2 = Math.min(x + width, r.x + r.width);
		int y2 = Math.min(y + height, r.y + r.height);

		return new Rect(x1, y1, x2 - x1, y2 - y1);
	}

	public Rect union(Rect r) {
		int x1 = Math.min(x, r.x);
		int y1 = Math.min(y, r.y);

		int x2 = Math.max(x + width, r.x + r.width);
		int y2 = Math.max(y + height, r.y + r.height);
		
		return new Rect(x1, y1, x2 - x1, y2 - y1);
	}
}
