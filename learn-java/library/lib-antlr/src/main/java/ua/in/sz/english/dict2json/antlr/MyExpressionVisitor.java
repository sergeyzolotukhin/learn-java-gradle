package ua.in.sz.english.dict2json.antlr;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MyExpressionVisitor extends ExpressionBaseVisitor<Integer> {
	Map<String, Integer> variables = new HashMap<>();

	@Override
	public Integer visitAssign(ExpressionParser.AssignContext ctx) {
		String id = ctx.identification().getText();
		int value = visit(ctx.expression());
		variables.put(id, value);

		log.debug("{} => {}", ctx.getText(), value);
		return value;
	}

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

		log.debug("{} => {}", ctx.getText(), value);
		return value;
	}

	@Override
	public Integer visitVar(ExpressionParser.VarContext ctx) {
		final String id = ctx.identification().getText();
		final Integer value = variables.get(id);

		log.trace("var: {} => {}", id, value);
		return value;
	}
}
