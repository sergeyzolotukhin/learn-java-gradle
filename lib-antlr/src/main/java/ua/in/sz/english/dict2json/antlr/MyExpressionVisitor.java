package ua.in.sz.english.dict2json.antlr;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyExpressionVisitor extends ExpressionBaseVisitor<Integer> {
	@Override
	public Integer visitIntLbl(ExpressionParser.IntLblContext ctx) {
		final Integer value = Integer.valueOf(ctx.getText());
		log.trace("Integer: {}", value);
		return value;
	}
}
