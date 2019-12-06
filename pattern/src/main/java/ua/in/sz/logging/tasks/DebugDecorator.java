package ua.in.sz.logging.tasks;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DebugDecorator {
	public static Runnable decorate(Runnable runnable) {
		return () -> {
			log.info("Debug decorator start");
			runnable.run();
			log.info("Debug decorator end");
		};
	}
}
