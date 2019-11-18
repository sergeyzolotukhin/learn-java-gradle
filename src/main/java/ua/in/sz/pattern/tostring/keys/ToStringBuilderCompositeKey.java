package ua.in.sz.pattern.tostring.keys;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import ua.in.sz.pattern.tostring.CompositeKey;

import java.util.HashMap;
import java.util.Map;

public class ToStringBuilderCompositeKey implements CompositeKey {
	private Map<String, Object> fields = new HashMap<>();
	private Map<String, Object> params;

	@Override
	public void addField(String name, Object value) {
		fields.put(name, value);
	}

	@Override
	public void addParam(String name, Object value) {
		fields.put(name, value);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.append("fields", fields)
				.append("params", params)
				.toString();
	}
}
