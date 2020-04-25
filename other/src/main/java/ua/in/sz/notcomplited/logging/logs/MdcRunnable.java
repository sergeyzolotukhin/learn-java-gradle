package ua.in.sz.notcomplited.logging.logs;

import org.slf4j.MDC;

import java.util.Map;

public class MdcRunnable implements Runnable {
	private final Runnable runnable;
	private final Map<String, String> context;

	public MdcRunnable(Runnable runnable) {
		this.runnable = runnable;

		context = MDC.getCopyOfContextMap();
	}

	@Override
	public void run() {
		Map<String, String> previous = MDC.getCopyOfContextMap();

		replace(context);
		try {
			runnable.run();
		} finally {
			replace(previous);
		}
	}

	private static void replace(Map<String, String> context) {
		if (context == null) {
			MDC.clear();
		} else {
			MDC.setContextMap(context);
		}
	}
}