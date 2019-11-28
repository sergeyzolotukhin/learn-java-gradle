package ua.in.sz.pattern.interpret.expression;

import ua.in.sz.pattern.interpret.api.Context;
import ua.in.sz.pattern.interpret.api.Entity;
import ua.in.sz.pattern.interpret.expression.impl.ContextImpl;
import ua.in.sz.pattern.interpret.expression.impl.CurrentEntityExpression;
import ua.in.sz.pattern.interpret.expression.impl.FutureEntityExpression;

import java.time.LocalDate;

public final class EntityExpressions {

	public static CurrentEntityExpression currentEntity(){
		return new CurrentEntityExpression();
	}

	public static FutureEntityExpression futureEntity(){
		return new FutureEntityExpression();
	}

	public static Context entityContext(Entity entity, LocalDate currentDate) {
		return new ContextImpl(entity, currentDate);
	}
}
