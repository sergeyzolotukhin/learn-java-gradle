package ua.in.sz.english.dict2json.antlr;

import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import ua.in.sz.english.dict2json.antlr.DictionaryParser.DefinitionContext;

import java.util.List;

@Slf4j
public class AntlrApplication {
	private static final String TEXT = "" +
			"aard-wolf ['a:d,wulfj] n земляной волк.\n" +
			"abashment [s'beejrmnt] n смуще'ние, замешательство.\n";

	public static void main(String[] args) {
		List<DefinitionContext> definitions = parse(TEXT);

		for (DefinitionContext definition : definitions) {
			String word = definition.word().getText();
			String partOfSpeech = definition.partOfSpeech().getText();
			String meaning = definition.meaning().getText();

			log.info("Word [{}] is part of speech [{}] and it have meaning [{}]",
					word, partOfSpeech, meaning);
		}
	}

	public static List<DefinitionContext> parse(String text) {
		DictionaryLexer lexer = new DictionaryLexer(CharStreams.fromString(text));
		DictionaryParser parser = new DictionaryParser(new CommonTokenStream(lexer));
		return parser.dictionary().definition();
	}
}
