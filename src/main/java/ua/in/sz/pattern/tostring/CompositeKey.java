package ua.in.sz.pattern.tostring;

public interface CompositeKey {
	void addField(String name, Object value);

	void addParam(String name, Object value);
}
