package ua.in.sz.pattern.interpret.expression.impl;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.pattern.interpret.api.Context;

import java.time.LocalDate;

@Slf4j
public class FutureEntityExpression extends AbstractEntityExpression {
	@Override
	public boolean evaluate(Context context) {
		LocalDate date = context.getCurrentDate();
		LocalDate from = context.getEntity().getFrom();

		return date.isBefore(from);
	}
}
