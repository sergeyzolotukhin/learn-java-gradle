package ua.in.sz.executor.service.future;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Slf4j
public class CompletableFutureMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        log.info("Starting");

        try (ExecutorService executor = Executors.newFixedThreadPool(10)) {
            List<CompletableFuture<String>> futures = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                futures.add(CompletableFuture.supplyAsync(new MySupplier("Task " + i, i + 1), executor));
            }
            log.info("Submitted");

            CompletableFuture<List<String>> allResults = CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new))
                    .thenApplyAsync(
                            v -> futures.stream()
                                    .map(CompletableFuture::join)
                                    .peek(s -> log.info("task result: {}", s))
                                    .collect(Collectors.toList()),
                            executor);

            log.info("Result: [{}]", String.join(", ", allResults.get()));
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
