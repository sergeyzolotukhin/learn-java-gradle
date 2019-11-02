package ua.in.sz.pattern.interpret.expression.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.pattern.interpret.api.Context;
import ua.in.sz.pattern.interpret.api.Expression;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class OrEntityExpression extends AbstractEntityExpression {
	private final List<Expression> expressions;

	@Override
	public boolean evaluate(Context context) {
		return expressions.stream().anyMatch(e -> e.evaluate(context));
	}
}
