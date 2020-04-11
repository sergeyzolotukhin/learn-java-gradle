package ua.in.sz.swing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {
	public static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		Object object = "Serhij";

		cast(object);
	}

	private static void cast(Object obj) {
		if (obj instanceof String s) {
			String substring = s.substring(2);
			log.info("Text: {}", substring);
		}
	}
}
