package ua.in.sz.quartz.calendar.whole;

import lombok.extern.slf4j.Slf4j;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

import java.io.IOException;
import java.io.InputStream;


/**
 * <a href="https://opennlp.sourceforge.net/models-1.5/">...</a>
 * <a href="https://dpdearing.com/posts/2011/12/opennlp-part-of-speech-pos-tags-penn-english-treebank/">...</a>
 */
@Slf4j
public class Application {
    public static void main(String[] args) {

        InputStream tokenModelIn = null;
        InputStream posModelIn = null;

        try {
            String sentence = "In the previous section, you learned about volumes as a mechanism " +
                    "to swap out mutable data of a container to store it separately from its " +
                    "own container";

            // tokenize the sentence
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            tokenModelIn = classloader.getResourceAsStream("en-token.bin");
            TokenizerModel tokenModel = new TokenizerModel(tokenModelIn);
            Tokenizer tokenizer = new TokenizerME(tokenModel);
            String tokens[] = tokenizer.tokenize(sentence);

            // Parts-Of-Speech Tagging
            posModelIn = classloader.getResourceAsStream("en-pos-maxent.bin");
            POSModel posModel = new POSModel(posModelIn);
            POSTaggerME posTagger = new POSTaggerME(posModel);
            String tags[] = posTagger.tag(tokens);

            for (int i = 0; i < tokens.length; i++) {
                log.info("{} -> {}", tokens[i], toName(tags[i]));
            }
        } catch (IOException e) {
            log.error("Failed", e);
        } finally {
            if (tokenModelIn != null) {
                try {
                    tokenModelIn.close();
                } catch (IOException ignore) {
                }
            }
            if (posModelIn != null) {
                try {
                    posModelIn.close();
                } catch (IOException ignore) {
                }
            }
        }
    }

    private static String toName(String tag) {
        switch (tag) {
            case "PRP":
            case "PRP$":
                return "pronoun";
            case "NN":
            case "NNS":
                return "noun";

            case "VB":
            case "VBD":
            case "VBZ":
                return "verb";

            case "DT":
                return "determiner";
            case "JJ":
                return "adjective";
            case "RB":
                return "adverb";
            case "IN":
                return "preposition"; // Preposition or subordinating conjunction
            case "RP":
                return "?"; // Particle
            case "TO":
                return "?"; // to
            default:
                return tag;
        }
    }
}
