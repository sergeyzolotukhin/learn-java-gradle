package ua.in.sz.pattern.gof.template.method.classic;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;


@Slf4j
public class Application {
	public static void main(String[] args) {
		AbstractTimeLoggingRunnable runnable = new MyRunnable();
		runnable.runWithTimeLogging();
	}

	@SneakyThrows
	private static void sleep() {
		TimeUnit.SECONDS.sleep(10);
	}

	private static class MyRunnable extends AbstractTimeLoggingRunnable {
		@Override
		public void run() {
			sleep();
		}
	}
}
