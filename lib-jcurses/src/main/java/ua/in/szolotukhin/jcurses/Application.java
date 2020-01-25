package ua.in.szolotukhin.jcurses;

import jcurses.system.Toolkit;
import jcurses.widgets.container.Panel;
import jcurses.widgets.window.Window;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application {
	public static void main(String[] args) throws InterruptedException {
		final int screenWidth = Toolkit.getScreenWidth();
		final int screenHeight = Toolkit.getScreenHeight();

		Window window = new Window(screenWidth, screenHeight);
		window.show();

		Panel rootPanel = window.getRootPanel();

		Thread.sleep(5_000);

		window.close();
	}
}
