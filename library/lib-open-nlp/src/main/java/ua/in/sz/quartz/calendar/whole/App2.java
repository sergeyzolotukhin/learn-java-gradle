package ua.in.sz.quartz.calendar.whole;

import lombok.extern.slf4j.Slf4j;
import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@Slf4j
public class App2 {

    public static void main(String[] args) {

        InputStream modelInParse = null;
        try {
            // load chunking model
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            modelInParse = classloader.getResourceAsStream("en-parser-chunking.bin");
            Objects.requireNonNull(modelInParse);
            ParserModel model = new ParserModel(modelInParse);

            // create parse tree
            Parser parser = ParserFactory.create(model);
            String sentence = "In the previous section, you learned about volumes as a mechanism " +
                    "to swap out mutable data of a container to store it separately from its " +
                    "own container";
            Parse[] topParses = ParserTool.parseLine(sentence, parser, 1);

            StringBuilder sb = new StringBuilder();
            for (Parse p : topParses) {
                log(sb, "", p);
            }
            log.info("\n{}", sb);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (modelInParse != null) {
                try {
                    modelInParse.close();
                } catch (IOException ignore) {
                }
            }
        }
    }

    public static void log(StringBuilder sb, String prefix, Parse p) {
        if ("TK".equals(p.getType())) {
            return;
        }
        sb.append("\n")
                .append("[").append(p.getType()).append("]\t")
                .append(prefix)
                .append(p.getCoveredText());

        for (Parse child : p.getChildren()) {
            log(sb, prefix + "\t", child);
        }
    }
}
