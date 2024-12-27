package ua.in.sz.executor.service.timeout;

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
 * <p>
 * this is a good pattern otherwise the interrupt bit is cleared by the catch
 * <a href="https://stackoverflow.com/questions/12060279/is-it-possible-to-interrupt-a-specific-thread-of-an-executorservice">...</a>
 */
@Slf4j
public class TaskTimeoutMain {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        log.info("Starting");

        try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
            Future<String> future = executor.submit(new InfiniteTask());
            log.info("Submitted");

            try {
                future.get(1, TimeUnit.MICROSECONDS);
            } catch (TimeoutException e) {
                log.info("Canceling");
                future.cancel(true);
            }

            log.info("Done {}, Canceled {}, state {}",
                    future.isDone(), future.isCancelled(), future.state());
        }

        log.info("Ended");
    }

    static class InfiniteTask implements Callable<String> {
        public String call() throws InterruptedException {
            //noinspection InfiniteLoopStatement
            while (true) {
                if (Thread.interrupted()) {
                    log.info("Task interrupted");
                    throw new InterruptedException();
                }
            }
        }
    }
}
