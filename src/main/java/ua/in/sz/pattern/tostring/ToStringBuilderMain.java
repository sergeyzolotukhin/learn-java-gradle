package ua.in.sz.pattern.tostring;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ToStringBuilderMain {
	public static void main(String[] args) {
		CompositeKey key = new CompositeKey();

		key.fields.put("keyA", "valA");
		key.fields.put("keyB", "valB");
		key.fields.put("keyC", "valC");

		key.params.put("keyC", "valC");

		log.info("key: {}", key);
	}

	static class CompositeKey {
		Map<String, Object> fields = new HashMap<>();
		Map<String, Object> params = new HashMap<>();

		@Override
		public String toString() {
			return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
					.append("fields", fields)
					.append("params", params)
					.toString();
		}
	}
}
