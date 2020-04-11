package ua.in.sz.pattern.gof.template.method;

import com.google.common.base.Stopwatch;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;


@Slf4j
public class Application {
	public static void main(String[] args) {
		runWithExecutionTimeLogging(Application::sleep);
	}

	@SneakyThrows
	private static void sleep() {
		TimeUnit.SECONDS.sleep(10);
	}

	public static void runWithExecutionTimeLogging(Runnable action) {
		Stopwatch watch = Stopwatch.createStarted();

		action.run();

		watch.stop();

		log.info("Execution took: {} ", watch);
	}
}
