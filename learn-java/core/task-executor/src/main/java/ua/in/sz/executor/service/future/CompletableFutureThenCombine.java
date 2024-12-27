package ua.in.sz.executor.service.future;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CompletableFutureThenCombine {
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

            CompletableFuture<String> future2 = CompletableFuture.supplyAsync(
                    () -> {
                        log.info("Edpresso starting");
                        sleep(3);
                        log.info("Edpresso ended");
                        return "Edpresso";
                    },
                    executor);

            CompletableFuture<String> future = future1.thenCombineAsync(future2,
                    (s1, s2) -> {
                        String result = s1 + " : " + s2;
                        log.info("thenCombine starting: {}", result);
                        sleep(3);
                        log.info("thenCombine ended: {}", result);
                        return result;
                    },
                    executor);

            log.info("Future 1: {}", future.join());
        }
    }

    @SneakyThrows
    private static void sleep(long timeout) {
        TimeUnit.SECONDS.sleep(timeout);
    }
}
