package ua.in.sz.code.gen.tools;

import java.util.ArrayList;
import java.util.List;

public class PicoWriter implements PicoWriterItem {
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

    public PicoWriter() {
        indents = 0;
    }

    private PicoWriter(int initialIndent) {
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

    public PicoWriter nl() {
        return w("");
    }

    public PicoWriter tab() {
        return this;
    }

    public PicoWriter w(String string) {
        lineNo++;
        sb.append(string);
        flush();

        return this;
    }

    public PicoWriter writeln(String string) {
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
            } else if (item instanceof PicoWriter) {
                lastRowWasBlank = ((PicoWriter) item).render(sb, indentBase, normalizeAdjacentBlankRows, lastRowWasBlank);
            } else {
                String string = item.toString();
                sb.append(string);
            }
        }

        return lastRowWasBlank;
    }
    // ================================================================================================================

    public final PicoWriter writer() {
        if (sb.length() > 0) {
            flush();
            lineNo++;
        }

        PicoWriter inner = new PicoWriter(indents);
        lines.add(inner);
        lineNo++;

        return inner;
    }

    public final PicoWriter writeln(PicoWriter inner) {
        if (sb.length() > 0) {
            flush();
            lineNo++;
        }

        adjustIndents(inner, this.indents);

        lines.add(inner);
        lineNo++;

        return this;
    }

    private void adjustIndents(PicoWriter inner, int indents) {
        if (inner != null) {
            for (PicoWriterItem item : inner.lines) {
                if (item instanceof PicoWriter) {
                    adjustIndents((PicoWriter) item, indents);
                } else if (item instanceof IndentedLine) {
                    IndentedLine il = (IndentedLine) item;
                    il._indent = il._indent + indents;
                }
            }
        }
    }

    public PicoWriter createDeferredIndentedWriter(String startLine, String endLine) {
        writeln(startLine);
        indentRight();
        PicoWriter ggg = writer();
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