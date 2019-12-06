package ua.in.sz.logging;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.logging.logs.Mdc;
import ua.in.sz.logging.tasks.DebugDecorator;
import ua.in.sz.logging.tasks.SecurityDecorator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Application {
	public static void main(String[] args) throws InterruptedException {
		Mdc.put().feature("HBM-00001");

		log.info("start execution");

		ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

		for (int i = 0; i < 3; i++) {
			executor.submit(
					Mdc.wrap(
							DebugDecorator.decorate(
									() -> log.info("Execute task"))));

			executor.submit(
					Mdc.wrap(
							SecurityDecorator.decorate(
									() -> log.info("Execute task"))));
		}

		Mdc.remove().feature();

		log.info("process continue");

		Mdc.put().feature("HBM-00002");

		for (int i = 0; i < 3; i++) {
			executor.submit(Mdc.wrap(
					SecurityDecorator.decorate(
							() -> log.info("Execute task"))));
		}

		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.MINUTES);

		log.info("end execution");

		Mdc.remove().feature();
	}
}
