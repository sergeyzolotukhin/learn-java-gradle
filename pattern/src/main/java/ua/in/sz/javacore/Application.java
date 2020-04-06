package ua.in.sz.javacore;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
public class Application {
	public static void main(String[] args) {
		List<String> lines = Arrays.asList("Line 1", "Line 2", "Line 1");

		int j;
		for (int i = 0; i < 10; i++) {
			Collections.shuffle(lines);

			if (lines.get(j = 1).equals(lines.get(++j))) {
				log.info("true");
			}
		}

		classNames();
	}

	private static void classNames() {
		log.info("{}", Application.class.getName());
		log.info("{}", Application.class.getSimpleName());
		log.info("{}", Application.class.getCanonicalName());
	}
}
