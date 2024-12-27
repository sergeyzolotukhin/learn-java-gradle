package ua.in.sz.h2.timeout;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * <a href="https://medium.com/@charleschang/java-executorservice-explained-9346b17ce8b4">Java ExecutorService explaine</a>
 */
@Slf4j
public class ExecutorServiceTaskTimeoutMain {
    public static void main(String[] args) {
        log.info("Starting");

        try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
            Future<String> future = executor.submit(new MyTask());
            log.info("Submitted");


            try {
                future.get(1, TimeUnit.MICROSECONDS);
            } catch (InterruptedException | ExecutionException e) {
                log.error(e.getMessage());
            } catch (TimeoutException e) {
                log.info("Canceling");
                future.cancel(true);
            }

            log.info("Done {}, Canceled {}, state {}",
                    future.isDone(), future.isCancelled(), future.state(), future.exceptionNow());
        }

        log.info("Ended");
    }

    static class MyTask implements Callable<String> {
        public String call() throws InterruptedException {
            for (int i = 0; i < 100000000; i++) {
                log.trace("executing {}", i);

                if (Thread.interrupted()) {
                    log.info("Task interrupted");
                    throw new InterruptedException();
                }
            }

            log.info("Task executed");
            return "Task executed";
        }
    }
}
