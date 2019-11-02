package ua.in.sz.pattern.interpret.expression.impl;

import com.google.common.collect.Lists;
import ua.in.sz.pattern.interpret.api.Entity;
import ua.in.sz.pattern.interpret.api.Expression;
import ua.in.sz.pattern.interpret.expression.EntityExpression;
import ua.in.sz.pattern.interpret.expression.EntityExpressions;

import java.time.LocalDate;

public abstract class AbstractEntityExpression implements EntityExpression {
	@Override
	public boolean evaluate(Entity entity, LocalDate currentDate) {
		return evaluate(EntityExpressions.entityContext(entity, currentDate));
	}

	@Override
	public EntityExpression or(Expression... expressions){
		return new OrEntityExpression(Lists.asList(this, expressions));
	}

	@Override
	public EntityExpression and(Expression... expressions){
		return new OrEntityExpression(Lists.asList(this, expressions));
	}
}
