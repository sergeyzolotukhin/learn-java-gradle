package jcurses.widgets.window;

import jcurses.util.Rectangle;
import jcurses.widgets.Widget;

import java.util.Comparator;

public class WindowWidgetComparator implements Comparator {

	// methods to compare widget positions
	static boolean toTheLeftOf(Widget widget1, Widget widget2) {
		Rectangle rect1 = widget1.getRectangle();
		Rectangle rect2 = widget2.getRectangle();
		return ((rect1.getX() + rect1.getWidth() - 1) < rect2.getX());
	}


	static boolean toTheRightOf(Widget widget1, Widget widget2) {
		Rectangle rect1 = widget1.getRectangle();
		Rectangle rect2 = widget2.getRectangle();
		return ((rect1.getX()) > (rect2.getX() + rect2.getWidth() - 1));
	}

	static boolean atTheTopOf(Widget widget1, Widget widget2) {
		Rectangle rect1 = widget1.getRectangle();
		Rectangle rect2 = widget2.getRectangle();
		return ((rect1.getY() + rect1.getHeight() - 1) < rect2.getY());
	}

	static boolean atTheBottomOf(Widget widget1, Widget widget2) {
		Rectangle rect1 = widget1.getRectangle();
		Rectangle rect2 = widget2.getRectangle();
		return ((rect1.getY()) > (rect2.getY() + rect2.getHeight() - 1));
	}

	static int getDistance(Widget widget1, Widget widget2) {
		Rectangle rect1 = widget1.getRectangle();
		Rectangle rect2 = widget2.getRectangle();
		int x1 = rect1.getX() + (rect1.getWidth() / 2);
		int y1 = rect1.getY() + (rect1.getHeight() / 2);
		int x2 = rect2.getX() + (rect2.getWidth() / 2);
		int y2 = rect2.getY() + (rect2.getHeight() / 2);

		return (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);

	}


	public int compare(Object obj1, Object obj2) {
		if ((!(obj1 instanceof Widget)) || (!(obj2 instanceof Widget))) {
			throw new RuntimeException("unknown classes to compare");
		}

		Widget widget1 = (Widget) obj1;
		Widget widget2 = (Widget) obj2;

		int result = 0;

		if (atTheTopOf(widget1, widget2)) {
			result = -1;
		} else if (atTheBottomOf(widget1, widget2)) {
			result = 1;
		} else {
			if (toTheLeftOf(widget1, widget2)) {
				result = -1;
			} else if (toTheRightOf(widget1, widget2)) {
				result = 1;
			} else {
				result = 0;
			}
		}


		return result;

	}

}