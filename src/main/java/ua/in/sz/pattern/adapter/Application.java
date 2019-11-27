package ua.in.sz.pattern.adapter;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.pattern.adapter.adapter.DelegateShapeAdapter;
import ua.in.sz.pattern.adapter.ownapi.Drawing;
import ua.in.sz.pattern.adapter.ownapi.Rectangle;
import ua.in.sz.pattern.adapter.thirdpartyapi.CircleShape;
import ua.in.sz.pattern.adapter.thirdpartyapi.DrawingShape;

import java.util.Arrays;

import static java.util.Collections.singletonList;

@Slf4j
public class Application {
	public static void main(String[] args) {
		Drawing ownDrawing = Drawing.builder()
				.shapes(Arrays.asList(
						new Rectangle(),
						new DelegateShapeAdapter(new CircleShape())))
				.build();

		ownDrawing.render();

		DrawingShape thirdDrawing = DrawingShape.builder()
				.shapes(singletonList(new CircleShape()))
				.build();

		thirdDrawing.draw();

	}
}
