package ua.in.szolotukhin.jcurses;

import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.fusesource.jansi.Ansi;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import static org.fusesource.jansi.Ansi.Color.BLUE;
import static org.fusesource.jansi.Ansi.Color.WHITE;
import static org.fusesource.jansi.Ansi.ansi;

public class Window {
	private final Terminal terminal;

	private int width;
	private int height;

	@SneakyThrows
	public Window() {
		terminal = TerminalBuilder.terminal();

		width = terminal.getWidth();
		height = terminal.getHeight();
	}

	public void show() {
		Ansi hello = ansi().eraseScreen()
				.cursor(0, 0);

		hello.bg(BLUE);

		hello.fg(WHITE).a("\u2554").a(StringUtils.repeat("\u2550", width - 2)).a("\u2557").cursorDownLine();
		for (int i = 0; i < (height - 3); i++) {
			hello.fg(WHITE).a("\u2551").a(StringUtils.repeat(" ", width - 2)).a("\u2551").cursorDownLine();
		}
		hello.fg(WHITE).a("\u2551").a(StringUtils.repeat("\u2550", width - 2)).a("\u255D").cursorDownLine();

		hello.reset();

		System.out.print(hello);
	}
}
