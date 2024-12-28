package ua.in.sz.executor.service.completable.future;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * <a href="https://www.baeldung.com/java-completablefuture-timeout">Java ExecutorService explaine</a>
 */
@Slf4j
public class MyCompletableFutureMain {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        log.info("Starting");

        try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
            MyCompletableFuture<String> future = MyCompletableFuture.supplyAsync(new InfiniteTask(), executor);

            MyCompletableFuture<String> future1 = future.thenComposeAsync(
                    (Function<String, MyCompletableFuture<String>>) s -> MyCompletableFuture.supplyAsync(
                            new SecondTask(s),
                            executor),
                    executor);

            log.info("Submitted");

            String result = future1.get();

            log.info("Done {}, Canceled {}, state {}, result {}",
                    future.isDone(), future.isCancelled(), future.state(), result);

        }

        log.info("Ended");
    }

    static record SecondTask(String s) implements Supplier<String> {
        @Override
        @SneakyThrows
        public String get() {
            String result = "Food Served" + s;
            log.info("Starting: {}", result);
            sleep(3);
            log.info("Ended: {}", result);
            return result;
        }
    }

    static class InfiniteTask implements Supplier<String> {
        @Override
        @SneakyThrows
        public String get() {
            log.info("InfiniteTask");
            return "InfiniteTask";
        }
    }

    @SneakyThrows
    private static void sleep(long timeout) {
        TimeUnit.SECONDS.sleep(timeout);
    }
}
