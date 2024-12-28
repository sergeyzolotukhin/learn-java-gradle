package ua.in.sz.executor.service.timeout;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * <a href="https://www.baeldung.com/java-completablefuture-timeout">Java ExecutorService explaine</a>
 */
@Slf4j
public class CompletableFutureTimeoutMain {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        log.info("Starting");

        try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
            CompletableFuture<String> future = CompletableFuture.supplyAsync(new InfiniteTask(), executor)
//                    .completeOnTimeout("timeout", 1, TimeUnit.SECONDS)
                    .orTimeout(1, TimeUnit.SECONDS)
                    .exceptionally(ex -> {
                        log.info("Timeout occurred: {}", ex.getMessage());
                        return "exceptionally";
                    });
            log.info("Submitted");

            String result = future.get();

            log.info("Done {}, Canceled {}, state {}, result {}",
                    future.isDone(), future.isCancelled(), future.state(), result);

        }

        log.info("Ended");
    }

    static class InfiniteTask implements Supplier<String> {
        @Override
        @SneakyThrows
        public String get() {
            for (int i = 0; i < 10; i++) {
                TimeUnit.MILLISECONDS.sleep(500);
                log.info("Waiting for {} seconds", 1);

                if (Thread.interrupted()) {
                    log.info("Task interrupted");
//                    throw new InterruptedException();
                    return null;
                }
            }

            return null;
        }
    }
}
