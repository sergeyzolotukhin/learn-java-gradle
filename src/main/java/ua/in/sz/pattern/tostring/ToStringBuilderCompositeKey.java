package ua.in.sz.pattern.tostring;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ToStringBuilderCompositeKey implements CompositeKey {
	Map<String, Object> fields = new HashMap<>();
	Map<String, Object> params = new HashMap<>();

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
