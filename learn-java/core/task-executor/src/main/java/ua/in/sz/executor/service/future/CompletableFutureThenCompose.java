package ua.in.sz.executor.service.future;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Slf4j
public class CompletableFutureThenCompose {
    public static void main(String[] args) {
        try (ExecutorService executor = Executors.newFixedThreadPool(10)) {
            CompletableFuture<String> future1 = CompletableFuture.supplyAsync(
                    () -> {
                        log.info("Educative starting");
                        sleep(2);
                        log.info("Educative ended");
                        return "Educative";
                    },
                    executor);

            CompletableFuture<String> future = future1.thenComposeAsync(
                    (Function<String, CompletableFuture<String>>) s -> CompletableFuture.supplyAsync(
                            () -> {
                                String result = "Food Served" + s;
                                log.info("Starting: {}", result);
                                sleep(3);
                                log.info("Ended: {}", result);
                                return result;
                            },
                            executor),
                    executor);

            log.info("result: {}", future.join());
        }
    }

    @SneakyThrows
    private static void sleep(long timeout) {
        TimeUnit.SECONDS.sleep(timeout);
    }
}
