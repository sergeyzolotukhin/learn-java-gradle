package ua.in.sz.logging;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// https://logback.qos.ch/manual/onJoran.html
@Slf4j
@AllArgsConstructor
public class Application {
	private String title;
	private String description;

	public static void main(String[] args) {
		log.atInfo()
				.setMessage("Error processing given user {} {} {}")
				.addArgument(1)
				.addArgument(2)
				.addArgument(new Application("My Name 1", "My Description 1"))
				.addKeyValue("key", "key_1")
				.addKeyValue("exception_class", Application.class.getSimpleName())
				.addKeyValue("error_message", "ex.getMessage()")
				.log();

		log.atInfo()
				.setMessage("Error processing given user {} {} {}")
				.addArgument(3)
				.addArgument(4)
				.addArgument(new Application("My Name 2", "My Description 2"))
				.addKeyValue("key", "key_2")
				.addKeyValue("exception_class", Application.class.getSimpleName())
				.addKeyValue("error_message", "ex.getMessage()")
				.log();
	}

	@Override
	public String toString() {
		return "Application{" +
				"title='" + title + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}
