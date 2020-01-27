package ua.in.szolotukhin.jcurses;

import jcurses.system.Toolkit;
import jcurses.widgets.container.Panel;
import jcurses.widgets.window.Window;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import java.lang.management.ManagementFactory;

import static org.fusesource.jansi.Ansi.Color.BLUE;
import static org.fusesource.jansi.Ansi.Color.WHITE;
import static org.fusesource.jansi.Ansi.Color.YELLOW;
import static org.fusesource.jansi.Ansi.ansi;

@Slf4j
public class Application {
	public static void main(String[] args) {
		jansiWindow();
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
		Ansi hello = ansi().eraseScreen()
				.cursor(0, 0)
				.bg(BLUE)
				.fg(WHITE).a("\u2554").a(StringUtils.repeat("\u2550",78)).a("\u2557").cursorDownLine()
				.fg(WHITE).a("\u2551").fg(YELLOW).a("Hello").a(" World").cursorDownLine()
				.fg(WHITE).a("\u2551")
				.fg(YELLOW).a("Hello").a(" World")
				.cursorDownLine()
				.reset()
				;

		System.out.print(hello);

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
