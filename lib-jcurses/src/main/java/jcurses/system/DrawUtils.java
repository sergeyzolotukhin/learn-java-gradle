package jcurses.system;

import jcurses.util.Rectangle;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DrawUtils {
	/**
	 * The method draws a border ( empty rectangle )
	 *
	 * @param rect  bounds of the border to be painted
	 * @param color color attributes of the border
	 */
	public static void drawBorder(Rectangle rect, CharColor color) {
		Toolkit.drawBorder(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), color);
	}
}
