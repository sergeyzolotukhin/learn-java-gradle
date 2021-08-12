package ua.in.sz.code.gen.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CodeWriter implements PicoWriterItem {
    private static final String SEP = "\n";
    private static final String TAB = "\t";
    private int indents = -1;
    private int lineNo = 0;
    private boolean _generateIfEmpty = true;
    private boolean _generate = true;
    boolean _normalizeAdjacentBlankRows = false;

    private boolean dirty = false;
    private final List<PicoWriterItem> lines = new ArrayList<>();
    private final StringBuilder sb = new StringBuilder();

    public CodeWriter() {
        indents = 0;
    }

    private CodeWriter(int initialIndent) {
        indents = Math.max(initialIndent, 0);
    }

    public void indentRight() {
        indents++;
    }

    public void indentLeft() {
        indents--;
        if (indents < 0) {
            indents = 0;
//            throw new RuntimeException("Local indent cannot be less than zero");
        }
    }


    // ================================================================================================================

    public CodeWriter wl() {
        return wl("");
    }

    public CodeWriter tab() {
        return this;
    }

    public CodeWriter wl(Consumer<CodeWriter> w) {
        return this;
    }

    public CodeWriter wl(String string) {
        lineNo++;
        sb.append(string);
        flush();

        return this;
    }

    public CodeWriter writeln(String string) {
        lineNo++;
        sb.append(string.trim());
        flush();
        return this;
    }

    private void flush() {
        lines.add(new IndentedLine(sb.toString(), indents));
        sb.setLength(0);
        dirty = false;
    }

    // ================================================================================================================

    public String toString(int indentBase) {
        StringBuilder sb = new StringBuilder();
        render(sb, indentBase, _normalizeAdjacentBlankRows, false /* lastRowWasBlank */);
        return sb.toString();
    }

    public String toString() {
        return toString(0);
    }

    private boolean render(StringBuilder sb, int indentBase, boolean normalizeAdjacentBlankRows, boolean lastRowWasBlank) {
        if (dirty) {
            flush();
        }

        if ((!isGenerate()) || ((!isGenerateIfEmpty()) && isMethodBodyEmpty())) {
            return lastRowWasBlank;
        }

        for (PicoWriterItem item : lines) {
            if (item instanceof IndentedLine) {
                IndentedLine il = (IndentedLine) item;
                String lineText = il.getLine();
                int indentLevelHere = indentBase + il.getIndent();
                boolean thisRowIsBlank = lineText.length() == 0;

                if (normalizeAdjacentBlankRows && lastRowWasBlank && thisRowIsBlank) {
                    // Don't write the line if we already had a blank line
                } else {
                    writeIndentedLine(sb, indentLevelHere, lineText);
                }

                lastRowWasBlank = thisRowIsBlank;
            } else if (item instanceof CodeWriter) {
                lastRowWasBlank = ((CodeWriter) item).render(sb, indentBase, normalizeAdjacentBlankRows, lastRowWasBlank);
            } else {
                String string = item.toString();
                sb.append(string);
            }
        }

        return lastRowWasBlank;
    }
    // ================================================================================================================

    public final CodeWriter writer() {
        if (sb.length() > 0) {
            flush();
            lineNo++;
        }

        CodeWriter inner = new CodeWriter(indents);
        lines.add(inner);
        lineNo++;

        return inner;
    }

    public final CodeWriter writeln(CodeWriter inner) {
        if (sb.length() > 0) {
            flush();
            lineNo++;
        }

        adjustIndents(inner, this.indents);

        lines.add(inner);
        lineNo++;

        return this;
    }

    private void adjustIndents(CodeWriter inner, int indents) {
        if (inner != null) {
            for (PicoWriterItem item : inner.lines) {
                if (item instanceof CodeWriter) {
                    adjustIndents((CodeWriter) item, indents);
                } else if (item instanceof IndentedLine) {
                    IndentedLine il = (IndentedLine) item;
                    il._indent = il._indent + indents;
                }
            }
        }
    }

    public CodeWriter createDeferredIndentedWriter(String startLine, String endLine) {
        writeln(startLine);
        indentRight();
        CodeWriter ggg = writer();
        indentLeft();
        writeln(endLine);
        dirty = true;
        lineNo += 2;
        return ggg;
    }

    public boolean isEmpty() {
        return lineNo == 0;
    }

    private static final void writeIndentedLine(final StringBuilder sb, final int indentBase, final String line) {
        for (int indentIndex = 0; indentIndex < indentBase; indentIndex++) {
            sb.append(TAB);
        }
        sb.append(line);
        sb.append(SEP);
    }


    public boolean isMethodBodyEmpty() {
        return lines.size() == 0 && sb.length() == 0;
    }

    public boolean isGenerateIfEmpty() {
        return _generateIfEmpty;
    }

    public void setGenerateIfEmpty(boolean generateIfEmpty) {
        _generateIfEmpty = generateIfEmpty;
    }

    public boolean isGenerate() {
        return _generate;
    }

    public void setGenerate(boolean generate) {
        _generate = generate;
    }


    public void setNormalizeAdjacentBlankRows(boolean normalizeAdjacentBlankRows) {
        _normalizeAdjacentBlankRows = normalizeAdjacentBlankRows;
    }





}