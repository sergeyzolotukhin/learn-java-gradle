package ua.in.sz.logging;

import lombok.extern.slf4j.Slf4j;

// https://logback.qos.ch/manual/onJoran.html
@Slf4j
public class Application {
	public static void main(String[] args) {
		log.atInfo()
				.setMessage("Error processing given user 1")
				.addKeyValue("key", "key_1")
				.addKeyValue("exception_class", Application.class.getSimpleName())
				.addKeyValue("error_message", "ex.getMessage()")
				.log();

		log.atInfo()
				.setMessage("Error processing given user 2")
				.addKeyValue("key", "key_2")
				.addKeyValue("exception_class", Application.class.getSimpleName())
				.addKeyValue("error_message", "ex.getMessage()")
				.log();
	}
}
