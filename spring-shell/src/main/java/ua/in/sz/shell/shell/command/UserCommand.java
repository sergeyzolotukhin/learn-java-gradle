package ua.in.sz.shell.shell.command;

import org.jline.reader.LineReader;
import org.jline.terminal.Terminal;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;
import org.springframework.context.annotation.Lazy;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.util.ArrayList;
import java.util.List;

@ShellComponent
public class UserCommand {

	private List<String> users = new ArrayList<>();

	private final LineReader lineReader;
	private final Terminal terminal;

	@Lazy
	public UserCommand(LineReader lineReader, Terminal terminal) {
		this.lineReader = lineReader;
		this.terminal = terminal;
	}

	@ShellMethod("Add user.")
	public String add() {
		String name = lineReader.readLine(red(String.format("name [%s]:", "root")));
		String password = lineReader.readLine(String.format("password [%s]:", "root"));

		users.add(name);

		return String.format("add user [%s/%s]", name, password);
	}

	@ShellMethod("Remove user.")
	@ShellMethodAvailability("removeAvailability")
	public String remove() {
		String name = lineReader.readLine(red(String.format("name [%s]:", "root")));

		users.remove(name);

		return String.format("removed user [%s]", name);
	}

	@ShellMethod("list user.")
	public void list() {
		print(red(String.format("Users (%d):", users.size())));
		for (String user : users) {
			print(blue(user));
		}
	}

	// ================================================================================================================
	// security
	// ================================================================================================================

	private Availability removeAvailability() {
		return users.size() > 0 ? Availability.available() : Availability.unavailable("No user");
	}

	// ================================================================================================================
	// private methods
	// ================================================================================================================

	private String red(String message) {
		AttributedStringBuilder attr = new AttributedStringBuilder();
		AttributedStyle fg = AttributedStyle.DEFAULT.foreground(AttributedStyle.RED);

		return attr.append(message, fg).toAnsi();
	}

	private String blue(String message) {
		AttributedStringBuilder attr = new AttributedStringBuilder();
		AttributedStyle fg = AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE);

		return attr.append(message, fg).toAnsi();
	}

	private void print(String text) {
		terminal.writer().println(text);
		terminal.flush();
	}
}
