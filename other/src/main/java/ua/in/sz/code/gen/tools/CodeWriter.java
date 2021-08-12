package ua.in.sz.code.gen.tools;

import java.util.function.Consumer;

public class CodeWriter implements PicoWriterItem {
    private final StringBuilder sb = new StringBuilder();

    // ================================================================================================================

    public CodeWriter wl() {
        return wl("");
    }

    public CodeWriter wl(Consumer<CodeWriter> w) {
        w.accept(this);
        return this;
    }

    public CodeWriter wl(String string) {
        sb.append(string);
        return this;
    }

    // ================================================================================================================

    public String toString() {
        return sb.toString();
    }

    // ================================================================================================================

    public final CodeWriter writer() {
        return this;
    }
}