package ua.in.sz.english.dict2json.antlr;

import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import ua.in.sz.english.dict2json.antlr.ExpressionParser.StatementContext;

import java.util.List;

@Slf4j
public class AntlrApplication {
	private static final String TEXT = "1 + 2\n" +
			"1 + 2 * 3\n";

	public static void main(String[] args) {
		List<StatementContext> statements = parse(TEXT);

		for (StatementContext statement : statements) {
			log.info("statement [{}]", statement.getText());
		}
	}

	public static List<StatementContext> parse(String text) {
		ExpressionLexer lexer = new ExpressionLexer(CharStreams.fromString(text));
		ExpressionParser parser = new ExpressionParser(new CommonTokenStream(lexer));
		return parser.program().statement();
	}
}
