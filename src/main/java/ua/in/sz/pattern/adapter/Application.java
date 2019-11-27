package ua.in.sz.pattern.adapter;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.pattern.adapter.adapter.CommonShapeAdapter;
import ua.in.sz.pattern.adapter.adapter.ShapeAdapter;
import ua.in.sz.pattern.adapter.ownapi.Drawing;
import ua.in.sz.pattern.adapter.ownapi.Rectangle;
import ua.in.sz.pattern.adapter.thirdpartyapi.CircleShape;
import ua.in.sz.pattern.adapter.thirdpartyapi.DrawingShape;

import java.util.Arrays;

@Slf4j
public class Application {
	public static void main(String[] args) {
		Drawing ownDrawing = Drawing.builder()
				.shapes(Arrays.asList(
						new Rectangle(),
						new ShapeAdapter(new CircleShape()),
						new CommonShapeAdapter(new CircleShape())))
				.build();

		ownDrawing.render();

		DrawingShape thirdDrawing = DrawingShape.builder()
				.shapes(Arrays.asList(
						new CircleShape(),
						new CommonShapeAdapter(new Rectangle())
				))
				.build();

		thirdDrawing.draw();

	}
}
