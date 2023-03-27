package ua.in.sz.logging;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.logging.logs.Mdc;
import ua.in.sz.logging.logs.MdcExecutorService;
import ua.in.sz.logging.tasks.DebugDecorator;
import ua.in.sz.logging.tasks.SecurityDecorator;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Application {
	public static void main(String[] args) throws InterruptedException {
		log.info("start execution");

		MdcExecutorService executor = new MdcExecutorService(
				Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));

		Mdc.put().feature("HBM-00001");
		log.info("add tasks");
		for (int i = 0; i < 3; i++) {
			Mdc.put().task(String.format("TASK-%d", i));
			executor.submit(DebugDecorator.decorate(() -> log.info("Execute task")));
			executor.submit(SecurityDecorator.decorate(() -> log.info("Execute task")));
			Mdc.remove().task();
		}
		Mdc.remove().feature();

		log.info("process continue");

		Mdc.put().feature("HBM-00002");
		log.info("add tasks");
		for (int i = 0; i < 3; i++) {
			executor.submit(SecurityDecorator.decorate(() -> log.info("Execute task")));
		}
		Mdc.remove().feature();

		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.MINUTES);

		log.info("end execution");
	}
}
