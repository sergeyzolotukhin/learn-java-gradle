package ua.in.sz.english.dict2json.antlr;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyExpressionVisitor extends ExpressionBaseVisitor<Integer> {
	@Override
	public Integer visitInt(ExpressionParser.IntContext ctx) {
		final Integer value = Integer.valueOf(ctx.getText());

		log.trace("Integer: {}", value);
		return value;
	}

	@Override
	public Integer visitAdd(ExpressionParser.AddContext ctx) {
		int left = visit(ctx.expression(0));
		int right = visit(ctx.expression(1));
		final int value = left + right;

		log.trace("{} + {} = {}", left, right, value);
		return value;
	}

	@Override
	public Integer visitMul(ExpressionParser.MulContext ctx) {
		int left = visit(ctx.expression(0));
		int right = visit(ctx.expression(1));
		final int value = left * right;

		log.trace("{} * {} = {}", left, right, value);
		return value;
	}

	@Override
	public Integer visitParens(ExpressionParser.ParensContext ctx) {
		final Integer value = visit(ctx.expression());;

		log.trace("({}) => {}", ctx.expression().getText(), value);
		return value;
	}

	@Override
	public Integer visitStmt(ExpressionParser.StmtContext ctx) {
		Integer value = visit(ctx.expression());

		log.debug("{} => {}", ctx.expression().getText(), value);
		return value;
	}
}
