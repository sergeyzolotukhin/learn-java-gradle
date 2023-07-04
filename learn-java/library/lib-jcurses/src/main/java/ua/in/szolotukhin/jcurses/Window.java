package ua.in.szolotukhin.jcurses;

import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.fusesource.jansi.Ansi;
import org.jline.terminal.Terminal;

import static org.fusesource.jansi.Ansi.Color.BLUE;
import static org.fusesource.jansi.Ansi.Color.WHITE;
import static org.fusesource.jansi.Ansi.ansi;

public class Window {
	public static final String UL_CORNER = "\u2554";
	public static final String UR_CORNER = "\u2557";
	public static final String DL_CORNER = "\u255A";
	public static final String DR_CORNER = "\u255D";

	public static final String H_LINE = "\u2550";
	public static final String V_LINE = "\u2551";

	private int width;
	private int height;

	@SneakyThrows
	public Window(Terminal terminal) {
		width = terminal.getWidth();
		height = terminal.getHeight();
	}

	public void show() {
		Ansi hello = ansi().eraseScreen()
				.cursor(0, 0);

		hello.bg(BLUE);

		hello.fg(WHITE).a(UL_CORNER).a(StringUtils.repeat(H_LINE, width - 2)).a(UR_CORNER).cursorDownLine();
		for (int i = 0; i < (height - 3); i++) {
			hello.fg(WHITE).a(V_LINE).cursorToColumn(width).a(V_LINE).cursorDownLine();
		}
		hello.fg(WHITE).a(DL_CORNER).a(StringUtils.repeat(H_LINE, width - 2)).a(DR_CORNER).cursorDownLine();

		hello.reset();

		System.out.print(hello);
	}
}
