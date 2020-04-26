package ua.in.sz.logging.logs;

import org.slf4j.MDC;

@SuppressWarnings("UnusedReturnValue")
public class Mdc {
	public static final String FEATURE = "feature";

	private static final Mdc INSTANCE = new Mdc();

	public static Mdc put() {
		return INSTANCE;
	}

	public static Mdc remove() {
		return INSTANCE;
	}

	public Mdc feature(String feature) {
		MDC.put(FEATURE, feature);
		return INSTANCE;
	}

	public Mdc feature() {
		MDC.remove(FEATURE);
		return INSTANCE;
	}
}
