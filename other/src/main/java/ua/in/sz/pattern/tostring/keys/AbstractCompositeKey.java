package ua.in.sz.pattern.tostring.keys;


import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import ua.in.sz.pattern.tostring.CompositeKey;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ToString
public abstract class AbstractCompositeKey implements CompositeKey {
	Map<String, Object> fields = new HashMap<>();
	Map<String, Object> params = new HashMap<>();

	@Override
	public void setFields(Map<String, Object> fields) {
		this.fields = fields;
	}

	@Override
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	@Override
	public void addField(String name, Object value) {
		fields.put(name, value);
	}

	@Override
	public void addParam(String name, Object value) {
		fields.put(name, value);
	}

	@Override
	public String hash() {
		return StringUtils.substring(UUID.randomUUID().toString(), 0, 8);
	}
}
