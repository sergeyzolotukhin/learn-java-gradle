package ua.in.sz.quartz.calendar.whole;

import lombok.extern.slf4j.Slf4j;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

import java.io.InputStream;
import java.util.Objects;


@Slf4j
public class Application {
    public static void main(String[] args) throws Exception {
        // https://opennlp.sourceforge.net/models-1.5/
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream("en-token.bin");
        Objects.requireNonNull(inputStream);
        TokenizerModel model = new TokenizerModel(inputStream);
        TokenizerME tokenizer = new TokenizerME(model);
        String[] tokens = tokenizer.tokenize("In the previous section, you learned about volumes as a mechanism " +
                "to swap out mutable data of a container to store it separately from its " +
                "own container. ");

        for (String token : tokens) {
            log.info("{}", token);
        }
    }
}
