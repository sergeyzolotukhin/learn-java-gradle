package jcurses.widgets.window;


import jcurses.widgets.Widget;
import jcurses.widgets.container.Panel;
import jcurses.event.window.WindowEvent;
import jcurses.event.window.WindowListener;
import jcurses.event.window.WindowListenerManager;
import jcurses.system.CharColor;
import jcurses.system.InputChar;
import jcurses.system.Toolkit;
import jcurses.util.Rectangle;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Vector;

/**
 * This class is a jcurses implementation of an text based window. An window
 * under jcurses is, differnt from other GUI libraries, not a widget, this
 * contains a panel ( a the called root panel ), that contains all widgets.
 * A window can, but doesn't must, have a border and a title.
 * All windows under jcurses are managed in a stack, the topmost visible window
 * window on the stack gets all input chars for handling, this is so called focus
 * window. If an window are created, it gets automatically to the top of the stack
 * and leaves the until an other window is created or explicitly brought to the top.
 */
@SuppressWarnings("rawtypes")
public class Window {

	private String title;
	private boolean border;
	private boolean visible = false;

	private Rectangle rectangle;
	private Panel rootPanel;

	// internal properties

	private Vector _focusableChilds = null;
	private int _currentIndex = -1;
	private boolean _hasShadow = true;
	private Vector _shortCutsList = new Vector();
	private Hashtable _shortCutsTable = new Hashtable();
	boolean _closed = false;

	private static InputChar __defaultClosingChar = new InputChar(27);//escape character
	private InputChar _closingChar = getDefaultClosingChar();
	private static CharColor __defaultTitleColors = new CharColor(CharColor.WHITE, CharColor.RED);
	private CharColor _titleColors = getDefaultTitleColors();
	private static CharColor __defaultBorderColors = new CharColor(CharColor.WHITE, CharColor.BLACK);
	private CharColor _borderColors = getDefaultBorderColors();
	private static CharColor __shadowColors = new CharColor(CharColor.BLACK, CharColor.BLACK);

	private static InputChar __defaultFocusChangeChar = new InputChar('\t');//tab character
	private InputChar _focusChangeChar = getDefaultFocusChangeChar();
	private static InputChar __upChar = new InputChar(InputChar.KEY_UP);
	private static InputChar __downChar = new InputChar(InputChar.KEY_DOWN);
	private static InputChar __leftChar = new InputChar(InputChar.KEY_LEFT);
	private static InputChar __rightChar = new InputChar(InputChar.KEY_RIGHT);

	// ================================================================================================================
	// constructors
	// ================================================================================================================

	/**
	 * The constructor
	 *
	 * @param x      the the top left corner's x coordinate
	 * @param y      the top left corner's y coordinate
	 * @param width  window's width
	 * @param height window's height
	 * @param border true, if the window has a border, false in other case
	 */
	public Window(int x, int y, int width, int height, boolean border, String title) {
		this.border = border;
		this.title = title;
		rectangle = new Rectangle(width, height);
		rectangle.setLocation(x, y);

		int x1 = border ? x + 1 : x;
		int y1 = border ? y + 1 : y;
		int w = border ? width - 2 : width;
		int h = border ? height - 2 : height;

		rootPanel = new Panel(w, h);
		rootPanel.setSize(new Rectangle(w, h));
		rootPanel.setX(x1);
		rootPanel.setY(y1);
		rootPanel.setWindow(this);

		WindowManager.createWindow(this);
	}

	public Window(int width, int height, boolean border, String title) {
		this((Toolkit.getScreenWidth() - width) / 2,
				(Toolkit.getScreenHeight() - height) / 2,
				width, height,
				border, title);
	}

	public Window(int width, int height) {
		this(
				(Toolkit.getScreenWidth() - width) / 2,
				(Toolkit.getScreenHeight() - height) / 2,
				width, height,
				true, ""
		);
	}

	// ================================================================================================================
	// public API
	// ================================================================================================================

	public void show() {
		setVisible(true);
	}

	public void hide() {
		setVisible(false);
	}

	public void setVisible(boolean value) {
		Window oldTop = WindowManager.getTopWindow();
		if (value) {
			pack();
			visible = value;
			WindowManager.makeWindowVisible(this, oldTop);
		} else {
			visible = value;
			WindowManager.makeWindowInvisible(this, oldTop);
		}
	}

	public boolean isVisible() {
		return visible;
	}

	/**
	 * The method closed the window, that is removes it from window stack an eventually from screen, if it was visible.
	 */
	public void close() {
		WindowManager.removeWindow(this);
	}

	// ================================================================================================================
	//
	// ================================================================================================================

	public void paint() {
		drawThingsIfNeeded();
		rootPanel.paint();
	}

	public void repaint() {
		drawThingsIfNeeded();
		rootPanel.repaint();
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	/**
	 * The method moves the window to the top of the stack
	 */
	public void moveToTheTop() {
		WindowManager.moveToTop(this);
	}

	private InputChar getDefaultClosingChar() {
		return __defaultClosingChar;
	}

	/**
	 * The method returns the character to close window.
	 * As default is escape characted defined
	 *
	 * @return window's closing character
	 */
	public InputChar getClosingChar() {
		return _closingChar;
	}

	/**
	 * The method defines a new window's closing character. Default is escape.
	 *
	 * @param character new  window's closing character
	 */
	public void setClosingChar(InputChar character) {
		_closingChar = character;
	}

	private InputChar getDefaultFocusChangeChar() {
		return __defaultFocusChangeChar;
	}

	/**
	 * The method returns the charater used to navigate (change the focus) between widgets
	 * within the window. Default is 'tab'
	 *
	 * @return window's focus changing charater
	 */
	public InputChar getFocusChangeChar() {
		return _focusChangeChar;
	}

	/**
	 * The method defined the charater used to navigate (change the focus) between widgets
	 * within the window. Default is 'tab'
	 *
	 * @param character new window's focus changing charater
	 */
	public void setFocusChangeChar(InputChar character) {
		_focusChangeChar = character;
	}

	private boolean isShortCut(InputChar inp) {
		return (_shortCutsList.indexOf(inp) != -1);
	}

	private Widget getWidgetByShortCut(InputChar inp) {
		return (Widget) _shortCutsTable.get(inp);
	}

	/**
	 * The method tries to close the window, after the user has typed 'escape'
	 * or an other closing character. The procedure is as following:
	 * If the window has listeners, than an event is sent to the listeners. The window
	 * can be closed bei listeners. Did'nt listeners close the window, in leaves open.
	 * Has the window no listeners, than the method closes it.
	 */
	public boolean tryToClose() {
		if (_listenerManager.countListeners() > 0) {
			_listenerManager.handleEvent(new WindowEvent(this, WindowEvent.CLOSING));
			return isClosed();
		} else {
			close();
			return true;
		}
	}

	/**
	 * @return true, if the window is already closed, false in othe case.
	 */
	public boolean isClosed() {
		return _closed;
	}

	/**
	 * The method is called by the libray to handle an input character, if the window has the focus.
	 */
	public void handleInput(InputChar inp) {
		if (inp.equals(getClosingChar())) {
			tryToClose();
		} else if (inp.equals(getFocusChangeChar())) {
			changeFocus();
		} else if (inp.equals(__upChar)) {
			Widget cur = getCurrentWidget();
			if (cur != null) {
				boolean result = cur.handleInput(inp);
				if (!result) {
					changeFocus(2);
				}
			}
		} else if (inp.equals(__downChar)) {
			Widget cur = getCurrentWidget();
			if (cur != null) {
				boolean result = cur.handleInput(inp);
				if (!result) {
					changeFocus(3);
				}
			}
		} else if (inp.equals(__leftChar)) {
			Widget cur = getCurrentWidget();
			if (cur != null) {
				boolean result = cur.handleInput(inp);
				if (!result) {
					changeFocus(0);
				}
			}
		} else if (inp.equals(__rightChar)) {
			Widget cur = getCurrentWidget();
			if (cur != null) {
				boolean result = cur.handleInput(inp);
				if (!result) {
					changeFocus(1);
				}
			}
		} else if (isShortCut(inp)) {
			Widget cur = getCurrentWidget();
			if (cur != null) {
				boolean result = cur.handleInput(inp);
				if (!result) {
					getWidgetByShortCut(inp).handleInput(inp);
				}
			}
		} else {
			if (!handleInputByCurrentChild(inp)) {
				onChar(inp);
			}
		}
	}

	/**
	 * The method is called by <code>handleInput</code>, if no widget has handled
	 * the input. Derived classes can override the method to define additional shortcuts.
	 */
	protected void onChar(InputChar inp) {
		//default nothing
	}

	private void changeFocus() {
		if (_currentIndex != -1) {
			int newIndex = (_currentIndex == (_focusableChilds.size() - 1)) ? 0 : (_currentIndex + 1);
			if (newIndex != _currentIndex) {
				((Widget) _focusableChilds.elementAt(_currentIndex)).setFocus(false);
				((Widget) _focusableChilds.elementAt(newIndex)).setFocus(true);
				_currentIndex = newIndex;
			}
		}
	}

	private void changeFocus(int direction) {
		if (_currentIndex != -1) {
			changeFocus(getNextWidget(direction));
		}
	}

	private Widget getNextWidget(int direction) {
		Widget result = getCurrentWidget();
		Widget current = getCurrentWidget();
		int x = result.getAbsoluteX();
		int y = result.getAbsoluteY();
		int searchDirection = ((direction == 0) || (direction == 2)) ? -1 : 1;

		Vector widgets = _focusableChilds;
		int index = widgets.indexOf(result);
		if (index < 0) {
			throw new RuntimeException("widget in the sorted queue not found!!");
		}

		int distance = Integer.MAX_VALUE;
		while (index < widgets.size() && index > -1) {
			index += searchDirection;
			if (index < widgets.size() && index > -1) {
				Widget candidate = (Widget) widgets.get(index);
				if (((direction == 0) && WindowWidgetComparator.toTheLeftOf(candidate, current)) ||
						((direction == 1) && WindowWidgetComparator.toTheRightOf(candidate, current)) ||
						((direction == 2) && WindowWidgetComparator.atTheTopOf(candidate, current)) ||
						((direction == 3) && WindowWidgetComparator.atTheBottomOf(candidate, current))) {
					int newDistance = WindowWidgetComparator.getDistance(candidate, current);
					if (newDistance < distance) {
						distance = newDistance;
						result = candidate;
					}
				}
			}

		}

		return result;

	}

	public void changeFocus(Widget widget) {
		int newIndex = _focusableChilds.indexOf(widget);
		if (newIndex != -1) {
			if (_currentIndex == -1) {
				widget.setFocus(true);
				_currentIndex = newIndex;
			} else if (_currentIndex == newIndex) {
			} else {
				widget.setFocus(true);
				((Widget) _focusableChilds.elementAt(_currentIndex)).setFocus(false);
				_currentIndex = newIndex;
			}

		}
	}

	private Widget getCurrentWidget() {
		if (_currentIndex != -1) {
			return ((Widget) _focusableChilds.elementAt(_currentIndex));
		} else {
			return null;
		}
	}

	private boolean handleInputByCurrentChild(InputChar inp) {
		if (_currentIndex != -1) {
			return ((Widget) _focusableChilds.elementAt(_currentIndex)).handleInput(inp);
		}

		return false;
	}

	private void loadShortcuts() {
		_shortCutsList.clear();
		_shortCutsTable.clear();

		Vector list = rootPanel.getListOfWidgetsWithShortCuts();
		for (int i = 0; i < list.size(); i++) {
			Widget widget = (Widget) list.elementAt(i);
			Vector shortCuts = widget.getShortCutsList();
			_shortCutsList.addAll(shortCuts);
			for (int j = 0; j < shortCuts.size(); j++) {
				_shortCutsTable.put(shortCuts.elementAt(j), widget);
			}
		}

	}

	private void loadFocusableChilds() {
		_focusableChilds = rootPanel.getListOfFocusables();
		if (_focusableChilds.size() == 0) {
			_currentIndex = -1;
		} else {
			Collections.sort(_focusableChilds, new WindowWidgetComparator());
			_currentIndex = 0;
			((Widget) _focusableChilds.elementAt(0)).setFocus(true);
		}
	}

	/**
	 * The method computes new window's layout.
	 * The method must already be called, if anything on the window building
	 * is changed, for example, an widget is removed or isn't more focusable
	 * ( because not visible or other ).
	 */
	public void pack() {
		cutIfNeeded();
		configureRootPanel();
		rootPanel.pack();
		loadFocusableChilds();
		loadShortcuts();
	}


	private void cutIfNeeded() {
		int maxWidth = Toolkit.getScreenWidth() - rectangle.getX() - (_hasShadow ? 1 : 0);
		int maxHeight = Toolkit.getScreenHeight() - rectangle.getY() - (_hasShadow ? 1 : 0);

		if (rectangle.getWidth() > maxWidth) {
			rectangle.setWidth(maxWidth);
		}

		if (rectangle.getHeight() > maxHeight) {
			rectangle.setHeight(maxHeight);
		}
	}

	/**
	 * @return the root panel of the window
	 */
	public Panel getRootPanel() {
		return rootPanel;
	}

	/**
	 * Sets the root panel of the window. This is the top most widget container in the
	 * window's widget hierarchy. It occupies the entire window out of the border (if exists ).
	 */
	public void setRootPanel(Panel root) {
		rootPanel = root;
		rootPanel.setWindow(this);
	}

	private void drawThingsIfNeeded() {
		if (border) {
			Toolkit.drawBorder(rectangle, getBorderColors());
		}

		paintTitle();

		if (hasShadow()) {
			Toolkit.drawRectangle(rectangle.getX() + rectangle.getWidth(),
					rectangle.getY() + 1,
					1,
					rectangle.getHeight(), getShadowColors());
			Toolkit.drawRectangle(rectangle.getX() + 1,
					rectangle.getY() + rectangle.getHeight(),
					rectangle.getWidth(),
					1, getShadowColors());
		}
	}

	private void paintTitle() {
		if (title != null) {
			CharColor color = getTitleColors();
			Toolkit.printString(title, rectangle.getX() + (rectangle.getWidth() - title.length()) / 2, rectangle.getY(), color);
		}
	}

	public CharColor getDefaultBorderColors() {
		return __defaultBorderColors;
	}

	public CharColor getBorderColors() {
		return _borderColors;
	}

	public void setBorderColors(CharColor colors) {
		_borderColors = colors;
	}

	public CharColor getDefaultTitleColors() {
		return __defaultTitleColors;
	}

	public CharColor getTitleColors() {
		return _titleColors;
	}

	public void setTitleColors(CharColor colors) {
		_titleColors = colors;
	}

	public void setShadow(boolean value) {
		_hasShadow = value;
	}

	public boolean hasShadow() {
		return _hasShadow;
	}

	private CharColor getShadowColors() {
		return __shadowColors;
	}

	private WindowListenerManager _listenerManager = new WindowListenerManager();

	public void addListener(WindowListener listener) {
		_listenerManager.addListener(listener);
	}

	public void removeListener(WindowListener listener) {
		_listenerManager.removeListener(listener);
	}

	/**
	 * The method is called, if the window gets focus.
	 */
	public void activate() {
		_listenerManager.handleEvent(new WindowEvent(this, WindowEvent.ACTIVATED));
	}

	/**
	 * The method is called, if the window loses focus.
	 */
	public void deactivate() {
		_listenerManager.handleEvent(new WindowEvent(this, WindowEvent.DEACTIVATED));
	}

	/**
	 * The method is called, if the window is closed.
	 */
	public void closed() {
		_closed = true;
		_listenerManager.handleEvent(new WindowEvent(this, WindowEvent.CLOSED));
	}

	protected void resize(int width, int height) {
		rectangle.setWidth(width);
		rectangle.setHeight(height);
	}

	// ================================================================================================================
	// private methods
	// ================================================================================================================

	private void configureRootPanel() {
		if (rootPanel == null) {
			rootPanel = new Panel();
		}

		int x = rectangle.getX();
		int y = rectangle.getY();
		int width = rectangle.getWidth();
		int height = rectangle.getHeight();

		int x1 = border ? x + 1 : x;
		int y1 = border ? y + 1 : y;
		int w = border ? width - 2 : width;
		int h = border ? height - 2 : height;

		rootPanel.setSize(new Rectangle(w, h));
		rootPanel.setX(x1);
		rootPanel.setY(y1);
	}
}
