package ua.in.szolotukhin.jcurses;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.fusesource.jansi.AnsiConsole;

import java.lang.management.ManagementFactory;

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

	@SneakyThrows
	private static void jansiWindow() {
//		if (isRunningFromIdea()) {
//			log.info("Running from IDEA");
//			System.setProperty("jansi.passthrough", "true");
//		}

		AnsiConsole.systemInstall();

		Window window = new Window();

		window.show();

		AnsiConsole.systemUninstall();
	}
}
