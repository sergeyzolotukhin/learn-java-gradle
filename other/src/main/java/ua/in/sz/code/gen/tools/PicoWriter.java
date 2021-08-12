package ua.in.sz.code.gen.tools;

import java.util.ArrayList;
import java.util.List;

public class PicoWriter implements PicoWriterItem {
    private static final String SEP = "\n";
    private static final String TAB = "\t";
    private int indents = -1;
    private int _numLines = 0;
    private boolean _generateIfEmpty = true;
    private boolean _generate = true;
    boolean _normalizeAdjacentBlankRows = false;

    private boolean _isDirty = false;
    private final List<PicoWriterItem> _content = new ArrayList<>();
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

    public PicoWriter w() {
        return w("");
    }

    public PicoWriter w(String string) {
        _numLines++;
        sb.append(string);
        flush();

        return this;
    }

    public PicoWriter writeln(String string) {
        _numLines++;
        sb.append(string.trim());
        flush();
        return this;
    }

    // ================================================================================================================

    public final PicoWriter createDeferredWriter() {
        if (sb.length() > 0) {
            flush();
            _numLines++;
        }

        PicoWriter inner = new PicoWriter(indents);
        _content.add(inner);
        _numLines++;

        return inner;
    }

    public final PicoWriter writeln(PicoWriter inner) {
        if (sb.length() > 0) {
            flush();
            _numLines++;
        }

        adjustIndents(inner, this.indents);

        _content.add(inner);
        _numLines++;

        return this;
    }

    private void adjustIndents(PicoWriter inner, int indents) {
        if (inner != null) {
            for (PicoWriterItem item : inner._content) {
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
        PicoWriter ggg = createDeferredWriter();
        indentLeft();
        writeln(endLine);
        _isDirty = true;
        _numLines += 2;
        return ggg;
    }

    public boolean isEmpty() {
        return _numLines == 0;
    }

    private static final void writeIndentedLine(final StringBuilder sb, final int indentBase, final String line) {
        for (int indentIndex = 0; indentIndex < indentBase; indentIndex++) {
            sb.append(TAB);
        }
        sb.append(line);
        sb.append(SEP);
    }

    private boolean render(StringBuilder sb, int indentBase, boolean normalizeAdjacentBlankRows, boolean lastRowWasBlank) {

        if (_isDirty) {
            flush();
        }

        // Some methods are flagged not to be generated if there is no body text inside the method, we don't add these to the class narrative
        if ((!isGenerate()) || ((!isGenerateIfEmpty()) && isMethodBodyEmpty())) {
            return lastRowWasBlank;
        }
        // TODO :: Will make this configurable
        for (PicoWriterItem item : _content) {
            if (item instanceof IndentedLine) {
                IndentedLine il = (IndentedLine) item;
                final String lineText = il.getLine();
                final int indentLevelHere = indentBase + il.getIndent();
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

    public boolean isMethodBodyEmpty() {
        return _content.size() == 0 && sb.length() == 0;
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

    private void flush(int indents) {
        _content.add(new IndentedLine(sb.toString(), indents));
        sb.setLength(0);
        _isDirty = false;
    }

    private void flush() {
        _content.add(new IndentedLine(sb.toString(), indents));
        sb.setLength(0);
        _isDirty = false;
    }


    public void setNormalizeAdjacentBlankRows(boolean normalizeAdjacentBlankRows) {
        _normalizeAdjacentBlankRows = normalizeAdjacentBlankRows;
    }

    public String toString(int indentBase) {
        StringBuilder sb = new StringBuilder();
        render(sb, indentBase, _normalizeAdjacentBlankRows, false /* lastRowWasBlank */);
        return sb.toString();
    }

    public String toString() {
        return toString(0);
    }


}