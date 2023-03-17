package ua.in.sz.logging;

import org.fusesource.jansi.AnsiConsole;

import static org.fusesource.jansi.Ansi.Color.GREEN;
import static org.fusesource.jansi.Ansi.Color.RED;
import static org.fusesource.jansi.Ansi.ansi;

// https://youtrack.jetbrains.com/issue/IDEA-132822
public class JansiMain {
    public static void main(String[] args) {
        System.setProperty("jansi.passthrough", "true");
        AnsiConsole.systemInstall();

        System.out.println( ansi().fg(RED).a("Hello").fg(GREEN).a(" World").reset() );

        AnsiConsole.systemUninstall();
    }
}
