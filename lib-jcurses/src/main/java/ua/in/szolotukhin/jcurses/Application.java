package ua.in.szolotukhin.jcurses;

import jcurses.system.Toolkit;
import jcurses.widgets.container.Panel;
import jcurses.widgets.window.Window;
import lombok.extern.slf4j.Slf4j;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import java.lang.management.ManagementFactory;

import static org.fusesource.jansi.Ansi.Color.GREEN;
import static org.fusesource.jansi.Ansi.Color.RED;
import static org.fusesource.jansi.Ansi.ansi;

@Slf4j
public class Application {
	public static void main(String[] args) throws InterruptedException {
//		jansiWindow();
		plainWindow();
	}

	private static boolean isRunningFromIdea() {
		return ManagementFactory.getRuntimeMXBean().getInputArguments()
				.stream()
				.filter(a -> a.startsWith("-javaagent:"))
				.anyMatch(a -> a.contains("idea_rt"));
	}

	private static void jansiWindow() {
		if (isRunningFromIdea()) {
			log.info("Running from IDEA");
			System.setProperty("jansi.passthrough", "true");
		}

		AnsiConsole.systemInstall();

		Ansi hello = ansi().eraseScreen().fg(RED).a("Hello").fg(GREEN).a(" World").reset();
		System.out.println(hello);

		AnsiConsole.systemUninstall();
	}

	private static final String FG_RED = "\u001B[31m";

	private static final String LU = "\u2554";
	private static final String V = "\u2550";
	private static final String RU = "\u2557";
	private static final String H = "\u2551";
	private static final String LD = "\u255A";
	private static final String RD = "\u2518";

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
