package ua.in.sz.swing.graphics2d;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.swing.graphics2d.model.Rect;

import javax.swing.*;
import java.awt.*;

@Slf4j
public class MainPanel extends JPanel {
	private Graphics2D g2d;

	@Override
	public void paint(Graphics g) {
		g2d = (Graphics2D) g;

		doPaint();
	}

	private void doPaint() {
		Rect rectA = Rect.of(0, 0, 100, 100);
		Rect rectB = Rect.of(10, 10, 100, 100);
		Rect rectC = rectA.intersection(rectB);
		Rect rectD = rectA.union(rectB);

		draw(rectD, Color.GREEN);
		draw(rectA, Color.BLUE);
		draw(rectB, Color.RED);
		draw(rectC, Color.YELLOW);
	}

	private void draw(Rect rectB, Color color) {
		int x = rectB.getX();
		int y = rectB.getY();
		int w = rectB.getWidth();
		int h = rectB.getHeight();

		g2d.setColor(color);
		g2d.fillRect(x, y, w, h);
//		g2d.drawRect(x, y, w, h);
	}
}
