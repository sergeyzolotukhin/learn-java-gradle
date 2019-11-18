package ua.in.sz.pattern.tostring;

import java.util.Map;

public interface CompositeKey {
	void addField(String name, Object value);

	void addParam(String name, Object value);

	void setFields(Map<String, Object> fields);

	void setParams(Map<String, Object> params);

	String hash();
}
