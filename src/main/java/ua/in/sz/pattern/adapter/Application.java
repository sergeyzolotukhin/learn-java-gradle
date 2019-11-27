package ua.in.sz.pattern.adapter;

import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.pattern.adapter.ownapi.Drawing;
import ua.in.sz.pattern.adapter.ownapi.Rectangle;
import ua.in.sz.pattern.adapter.thirdpartyapi.CircleShape;
import ua.in.sz.pattern.adapter.thirdpartyapi.DrawingShape;

@Slf4j
public class Application {
	public static void main(String[] args) {
		Drawing ownDrawing = Drawing.builder()
				.shapes(ImmutableList.of(new Rectangle()))
				.build();

		ownDrawing.render();

		DrawingShape thirdDrawing = DrawingShape.builder()
				.shapes(ImmutableList.of(new CircleShape()))
				.build();

		thirdDrawing.draw();

	}
}
