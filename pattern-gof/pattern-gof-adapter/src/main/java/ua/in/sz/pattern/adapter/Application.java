package ua.in.sz.pattern.adapter;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.pattern.adapter.adapter.CommonShapeAdapter;
import ua.in.sz.pattern.adapter.adapter.ShapeAdapter;
import ua.in.sz.pattern.adapter.ownapi.Drawing;
import ua.in.sz.pattern.adapter.ownapi.OwnRectangle;
import ua.in.sz.pattern.adapter.thirdpartyapi.ThirdCircleShape;
import ua.in.sz.pattern.adapter.thirdpartyapi.DrawingShape;

import java.util.Arrays;

@Slf4j
public class Application {
	public static void main(String[] args) {
		Drawing ownDrawing = Drawing.builder()
				.shapes(Arrays.asList(
						new OwnRectangle(),
						new ShapeAdapter(new ThirdCircleShape()),
						new CommonShapeAdapter(new ThirdCircleShape())))
				.build();

		ownDrawing.render();

		DrawingShape thirdDrawing = DrawingShape.builder()
				.shapes(Arrays.asList(
						new ThirdCircleShape(),
						new CommonShapeAdapter(new OwnRectangle())
				))
				.build();

		thirdDrawing.draw();

	}
}
