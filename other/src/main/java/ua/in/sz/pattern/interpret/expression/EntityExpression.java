package ua.in.sz.pattern.interpret.expression;


import ua.in.sz.pattern.interpret.api.Entity;
import ua.in.sz.pattern.interpret.api.Expression;

import java.time.LocalDate;

public interface EntityExpression extends Expression {
	boolean evaluate(Entity entity, LocalDate currentDate);
}
