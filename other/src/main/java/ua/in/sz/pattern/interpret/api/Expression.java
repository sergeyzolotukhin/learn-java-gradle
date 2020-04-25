package ua.in.sz.pattern.interpret.api;

public interface Expression {
	boolean evaluate(Context context);

	Expression or(Expression ... expressions);

	Expression and(Expression ... expressions);
}
