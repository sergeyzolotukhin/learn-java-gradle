package ua.in.szolotukhin.jcurses;

import jcurses.system.Toolkit;
import jcurses.widgets.container.Panel;
import jcurses.widgets.window.Window;
import lombok.extern.slf4j.Slf4j;
import org.fusesource.jansi.AnsiConsole;

@Slf4j
public class Application {
	public static void main(String[] args) throws InterruptedException {
//		System.setProperty("jansi.passthrough", "true");

//		AnsiConsole.systemInstall();
//		Ansi hello = ansi().eraseScreen().fg(RED).a("Hello").fg(GREEN).a(" World").reset();
//		System.out.println(hello);

//		System.out.println("\u001B[31mHello");

		plainWindow();
//		AnsiConsole.systemUninstall();
	}

	private static final String FG_RED = "\u001B[31m";

	private static final String LU = "\u2554";
	private static final String V = "\u2550";
	private static final String RU = "\u2557";
	private static final String H = "\u2551";
	private static final String LD = "\u255A";
	private static final String RD = "\u255D";

	private static void plainWindow() {
		System.out.println(FG_RED + LU + V + RU);
		System.out.println(H + " " + H);
		System.out.println(LD + V + RD);
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
