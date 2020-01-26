package ua.in.szolotukhin.jcurses;

import jcurses.system.Toolkit;
import jcurses.widgets.container.Panel;
import jcurses.widgets.window.Window;
import lombok.extern.slf4j.Slf4j;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import static org.fusesource.jansi.Ansi.Color.GREEN;
import static org.fusesource.jansi.Ansi.Color.RED;
import static org.fusesource.jansi.Ansi.ansi;

@Slf4j
public class Application {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("jansi.passthrough", "true");

		AnsiConsole.systemInstall();
		Ansi hello = ansi().eraseScreen().fg(RED).a("Hello").fg(GREEN).a(" World").reset();
		System.out.println(hello);

		System.out.println("\u001B[31mHello 2\u001B[32mSerhij");

		AnsiConsole.systemUninstall();
	}

	private static void showWindow() throws InterruptedException {
		final int screenWidth = Toolkit.getScreenWidth();
		final int screenHeight = Toolkit.getScreenHeight();

		Window window = new Window(screenWidth, screenHeight);
		window.show();

		Panel rootPanel = window.getRootPanel();

		Thread.sleep(5_000);

		window.close();
	}
}
