package ua.in.sz.quartz.calendar.whole;

import lombok.extern.slf4j.Slf4j;
import opennlp.tools.lemmatizer.DictionaryLemmatizer;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

import java.io.*;

/**
 * Lemmatization links similar meaning words as one word
 * <a href="https://stackoverflow.com/questions/55391121/opennlp-unable-to-locate-the-model-file-for-lemmatizer">
 * OpenNLP: Unable to locate the model file for Lemmatizer
 * </a>
 * <a href="https://opennlp.apache.org/docs/1.8.0/manual/opennlp.html#tools.cli.lemmatizer.LemmatizerME">
 * Chapter 7. Lemmatizer
 * </a>
 * <a href="https://opennlp.sourceforge.net/models-1.5/">
 * Use the links in the table below to download the pre-trained models for the OpenNLP 1.5 series
 * </a>
 * <a href="https://subscription.packtpub.com/book/web-development/9781789801156/1/ch01lvl1sec08/training-an-opennlp-lemmatization-model">
 * Training an OpenNLP lemmatization model
 * </a>
 */
@Slf4j
public class DictionaryLemmatizerExample {
    public static void main(String[] args) throws Exception {
        String sentence = "In the previous section, you learned about volumes as a mechanism " +
                "to swap out mutable data of a container to store it separately from its " +
                "own container";

        // tokenize the sentence
        InputStream tokenModelIn = getInputStream("en-token.bin");
        TokenizerModel tokenModel = new TokenizerModel(tokenModelIn);
        Tokenizer tokenizer = new TokenizerME(tokenModel);
        String tokens[] = tokenizer.tokenize(sentence);

        // Parts-Of-Speech Tagging
        InputStream posModelIn = getInputStream("en-pos-maxent.bin");
        POSModel posModel = new POSModel(posModelIn);
        POSTaggerME posTagger = new POSTaggerME(posModel);
        String tags[] = posTagger.tag(tokens);

        // Lemmatization
        InputStream dictLemmatizer = getInputStream("en-lemmatizer.txt");
        DictionaryLemmatizer lemmatizer = new DictionaryLemmatizer(dictLemmatizer);

        // finding the lemmas
        String[] lemmas = lemmatizer.lemmatize(tokens, tags);

        // printing the results
        log.info("\nPrinting lemmas for the given sentence...");
        log.info("WORD\t\t-POSTAG\t\tLEMMA");
        for (int i = 0; i < tokens.length; i++) {
            log.info(tokens[i] + "\t\t" + tags[i] + "\t\t" + lemmas[i]);
        }
    }

    public static InputStream getInputStream(String name) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        return classloader.getResourceAsStream(name);
    }
}
