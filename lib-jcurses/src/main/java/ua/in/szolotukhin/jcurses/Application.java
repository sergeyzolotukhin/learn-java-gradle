package ua.in.szolotukhin.jcurses;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.fusesource.jansi.AnsiConsole;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

@Slf4j
public class Application {
	public static void main(String[] args) {
		jansiWindow();
	}

	@SneakyThrows
	private static void jansiWindow() {
		AnsiConsole.systemInstall();

		Terminal terminal = TerminalBuilder.terminal();;

		Window window = new Window(terminal);

		window.show();

		LineReader lineReader = LineReaderBuilder.builder()
				.terminal(terminal)
				.option(LineReader.Option.ERASE_LINE_ON_FINISH, true)
				.build();

		while (true) {
			String line = lineReader.readLine("do>");
			if ("exit".equalsIgnoreCase(line)) {
				break;
			}
		}

		AnsiConsole.systemUninstall();
	}
}
