package ua.in.sz.executor.service.future;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Slf4j
public class CompletableFutureMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        log.info("Starting");

        try (ExecutorService executor = Executors.newFixedThreadPool(10)) {
            CompletableFuture<String> future1 = CompletableFuture.supplyAsync(new MySupplier("Task 1", 1), executor);
            CompletableFuture<String> future2 = CompletableFuture.supplyAsync(new MySupplier("Task 2", 2), executor);
            CompletableFuture<String> future3 = CompletableFuture.supplyAsync(new MySupplier("Task 3", 3), executor);
            log.info("Submitted");
            CompletableFuture<Void> allOf = CompletableFuture.allOf(future1, future2, future3);

            log.info("Result: [{}]", allOf.get());
        }

        log.info("Ended");
    }

    @Slf4j
    private record MySupplier(String name, int timeout) implements Supplier<String> {
        @SneakyThrows
        @Override
        public String get() {
            log.info("starting: [{}]", name);

            TimeUnit.SECONDS.sleep(timeout);

            log.info("ended: [{}]", name);
            return "Hello " + name;
        }
    }
}
