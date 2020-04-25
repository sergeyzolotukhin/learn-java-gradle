package jcurses.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This is a class to represent an screen rectangle.
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
public class Rectangle {
	// the x coordinate of the top left corner
	private int x;
	// the y coordinate of the top left corner
	private int y;
	private int width;
	private int height;

	public Rectangle(int width, int height) {
		this.width = width;
		this.height = height;
	}

	/**
	 * @return <code>true</code> if the rectangle is empty in other case <code>false</code>
	 */
	public boolean isEmpty() {
		return (width <= 0) || (height <= 0);
	}

	/**
	 * The method verifies, whether a rectangle lies within this rectangle
	 *
	 * @param X x coordinate of the rectangle, whose containment is to verify
	 * @param Y y coordinate of the rectangle, whose containment is to verify
	 * @param W width of the rectangle, whose containment is to verify
	 * @param H x height of the rectangle, whose containment is to verify
	 * @return <code>true</code> if the parameter rectangle is withhin this rectangle in other case <code>false</code>
	 */
	public boolean contains(int X, int Y, int W, int H) {
		if (width <= 0 || height <= 0 || W <= 0 || H <= 0) {
			return false;
		}

		return (X >= x &&
				Y >= y &&
				X + W <= x + width &&
				Y + H <= y + height);
	}

	/**
	 * The method veriifies, whether a rectangle lies within this rectangle
	 *
	 * @param rect the rectangle, whose containment is to verify
	 * @return <code>true</code> if the parameter rectangle is withhin this rectangle in other case <code>false</code>
	 */
	public boolean contains(Rectangle rect) {
		return contains(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
	}


	/**
	 * The method returns an intersection of the rectangle with an other rectangle,
	 * that is, the greatest rectangle, that is contained in both.
	 *
	 * @param r rectangle to build intersection with this rectangle
	 * @return the intersection rectangle
	 */
	public Rectangle intersection(Rectangle r) {
		if (isEmpty()) {
			return (Rectangle) this.clone();
		} else if (r.isEmpty()) {
			return (Rectangle) r.clone();
		} else {
			int x1 = Math.max(x, r.getX());
			int x2 = Math.min(x + width, r.getX() + r.getWidth());
			int y1 = Math.max(y, r.getY());
			int y2 = Math.min(y + height, r.getY() + r.getHeight());
			if (((x2 - x1) < 0) || ((y2 - y1) < 0))
				// Width or height is negative. No intersection.
				return new Rectangle(0, 0, 0, 0);
			else
				return new Rectangle(x1, y1, x2 - x1, y2 - y1);
		}
	}


	/**
	 * The method returns an union of the rectangle with an other rectangle,
	 * that is, the smallest rectangle, that contains both.
	 *
	 * @param r rectangle to build union with this rectangle
	 * @return the union rectangle
	 */
	public Rectangle union(Rectangle r) {
		if (isEmpty()) {
			return (Rectangle) r.clone();
		} else if (r.isEmpty()) {
			return (Rectangle) this.clone();
		} else {
			int x1 = Math.min(x, r.getX());
			int x2 = Math.max(x + width, r.getX() + r.getWidth());
			int y1 = Math.min(y, r.getY());
			int y2 = Math.max(y + height, r.getY() + r.getHeight());
			return new Rectangle(x1, y1, x2 - x1, y2 - y1);
		}
	}


	/**
	 * The method veriifies, whether a point lies within this rectangle
	 *
	 * @param x x coordinate of the point, whose containment is to verify
	 * @param y y coordinate of the point, whose containment is to verify
	 * @return <code>true</code> if the point is withhin this rectangle in other case <code>false</code>
	 */
	public boolean inside(int x, int y) {
		return (x >= this.x) && ((x - this.x) < width) && (y >= this.y) && ((y - this.y) < height);
	}


	/**
	 * Sets the location of the rectangle
	 *
	 * @param x new x coordinate
	 * @param y new y coordinate
	 */
	public void setLocation(int x, int y) {
		setX(x);
		setY(y);
	}


	/**
	 * Changes the size of the rectangle
	 *
	 * @param width  new width
	 * @param height new height
	 */
	public void resize(int width, int height) {
		setWidth(width);
		setHeight(height);
	}


	public Object clone() {
		return new Rectangle(x, y, width, height);
	}

}
