package ua.in.sz.shell.shell;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.ansi.AnsiStyle;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

@Slf4j
public class ApplicationBanner implements Banner {
	@Override
	public void printBanner(Environment env, Class<?> sourceClass, PrintStream out) {
		out.println(AnsiOutput.toString(
				AnsiColor.GREEN, "tools",
				AnsiColor.DEFAULT, " ",
				AnsiStyle.FAINT, "1.0"));
		out.println();
	}
}
