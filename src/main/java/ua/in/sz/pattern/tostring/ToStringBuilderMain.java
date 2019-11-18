package ua.in.sz.pattern.tostring;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.pattern.tostring.keys.ToStringBuilderCompositeKey;
import ua.in.sz.pattern.tostring.values.NoToStringValue;

@Slf4j
public class ToStringBuilderMain {
	public static void main(String[] args) {
		CompositeKey key = new ToStringBuilderCompositeKey();

		key.addField("keyA", "valA");
		key.addField("keyB", "valB");
		key.addField("keyC", new NoToStringValue("C"));

//		key.addParam("keyC", "valC");

		log.info("key: {}", key);
	}
}
