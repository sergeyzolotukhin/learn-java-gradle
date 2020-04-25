package ua.in.sz.swing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {
	public static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {

		int n = 7;
		int no = 0;
//		no = switchExpression(n);
		no = switchReturn(n);


		doSwitch(no);
//		cast("Serhij");
	}

	private static int switchReturn(int n) {
		return switch (n) {
			case 7,15 -> 2;
			default -> {
				log.info("Default run");
				yield 7;
			}
		};
	}

	private static int switchExpression(int n) {
		int no = switch (n) {
			case 7,15 -> 2;
			default -> 1;
		};

		return no;
	}

	private static void doSwitch(int no) {
		switch (no) {
			case 1,2 -> log.info("One or Two");
			default -> log.info("Other");
		}
	}

/*	private static void cast(Object obj) {
		if (obj instanceof String s) {
			String substring = s.substring(2);
			log.info("Text: {}", substring);
		}
	}*/
}
