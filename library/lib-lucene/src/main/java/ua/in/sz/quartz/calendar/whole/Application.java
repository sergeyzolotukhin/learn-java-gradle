package ua.in.sz.quartz.calendar.whole;

import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;

@Slf4j
public class Application {
	public static void main(String[] args) throws IOException {
		log.info("Start");

		String text = "The stem is the part of the word that never changes even when morphologically inflected; a lemma is the base form of the word. For example, from \"produced\", the lemma is \"produce\", but the stem is \"produc-\". This is because there are words such as production";
		Analyzer analyzer = new EnglishAnalyzer();
		TokenStream stream = analyzer.tokenStream("field", text);
		stream.reset();
		while (stream.incrementToken()) {
			String lemma = stream.getAttribute(CharTermAttribute.class).toString();
			log.info("{}", lemma);
		}
		stream.end();
		stream.close();

		log.info("End");
	}
}
