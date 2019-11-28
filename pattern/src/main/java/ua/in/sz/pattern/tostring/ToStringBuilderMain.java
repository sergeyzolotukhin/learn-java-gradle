package ua.in.sz.pattern.tostring;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.pattern.tostring.keys.LombokCompositeKey;
import ua.in.sz.pattern.tostring.keys.StringJoinerCompositeKey;
import ua.in.sz.pattern.tostring.keys.ToStringBuilderCompositeKey;
import ua.in.sz.pattern.tostring.values.LombokValue;
import ua.in.sz.pattern.tostring.values.NoToStringValue;
import ua.in.sz.pattern.tostring.values.StringJoinerValue;

@Slf4j
public class ToStringBuilderMain {
	public static void main(String[] args) {
//		CompositeKey key = new ToStringBuilderCompositeKey();
		CompositeKey key = new LombokCompositeKey();

		key.addField("keyA", "valA");
		key.addField("keyB", "valB");
//		key.addField("keyC", new NoToStringValue("C"));
//		key.addField("keyD", new StringJoinerValue("D"));
		key.addField("keyE", new LombokValue("E"));

//		key.addParam("keyC", "valC");
		key.setParams(null);

		log.info("key: {}", key);

		key = new StringJoinerCompositeKey();

		key.addField("keyA", "valA");
		key.addField("keyB", "valB");
		key.addField("keyC", new NoToStringValue("C"));

		key.addParam("keyC", "valC");

		log.info("key: {}", key);
	}
}
