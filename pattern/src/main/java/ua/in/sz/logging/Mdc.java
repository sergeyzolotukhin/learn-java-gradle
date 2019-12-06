package ua.in.sz.logging;

import org.slf4j.MDC;

import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

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

	// ================================================================================================================
	// static methods
	// ================================================================================================================

	public static Runnable wrap(Runnable runnable) {
		Map<String, String> context = MDC.getCopyOfContextMap();

		return () -> {
			Map<String, String> previous = MDC.getCopyOfContextMap();

			replace(context);
			try {
				runnable.run();
			} finally {
				replace(previous);
			}
		};
	}

	private static void replace(Map<String, String> context) {
		if (context == null) {
			MDC.clear();
		} else {
			MDC.setContextMap(context);
		}
	}
}
