package ua.in.sz.pattern.reflections;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class InnerHashMap {
	public static void main(String[] args) {
		InnerHashMap instance = new InnerHashMap();
		Map<String, Object> result = instance.parameters();

		log.info("parameters:");
		Logs.classes(result.getClass());

		log.info("this:");
		Logs.classes(InnerHashMap.class);
	}

	private Map<String, Object> parameters() {
		return new HashMap<String, Object>() {{
			put("1", getText());
		}};
	}

	private String getText() {
		return "Serhij";
	}
}
