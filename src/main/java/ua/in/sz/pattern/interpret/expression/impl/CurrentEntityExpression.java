package ua.in.sz.pattern.interpret.expression.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.pattern.interpret.api.Context;

import java.time.LocalDate;

@Slf4j
@RequiredArgsConstructor
public class CurrentEntityExpression extends AbstractEntityExpression {

	@Override
	public boolean evaluate(Context context) {
		LocalDate date = context.getCurrentDate();

		LocalDate from = context.getEntity().getFrom();
		LocalDate to = context.getEntity().getTo();

		return (date.isEqual(from) || date.isAfter(from))
				&& date.isBefore(to);
	}
}
