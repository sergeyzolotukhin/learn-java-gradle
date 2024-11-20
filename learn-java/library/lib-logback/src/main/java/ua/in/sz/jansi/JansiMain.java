package ua.in.sz.jansi;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import static org.fusesource.jansi.Ansi.Color.GREEN;
import static org.fusesource.jansi.Ansi.Color.RED;
import static org.fusesource.jansi.Ansi.ansi;

// https://youtrack.jetbrains.com/issue/IDEA-132822
public class JansiMain {
    public static void main(String[] args) {
        System.setProperty("jansi.passthrough", "true");
        AnsiConsole.systemInstall();

        Ansi ansi = ansi().fg(RED).a("Hello").fg(GREEN).a(" World").reset();
        System.out.println(ansi.toString());

        AnsiConsole.systemUninstall();
    }
}
