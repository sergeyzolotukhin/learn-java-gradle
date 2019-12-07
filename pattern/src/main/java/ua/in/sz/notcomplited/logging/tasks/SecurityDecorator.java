package ua.in.sz.notcomplited.logging.tasks;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityDecorator {
	public static Runnable decorate(Runnable runnable) {
		return () -> {
			log.info("Security decorator start");
			runnable.run();
			log.info("Security decorator end");
		};
	}
}
