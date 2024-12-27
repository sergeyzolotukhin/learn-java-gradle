package ua.in.sz.executor.service.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class CompletableFutureThenCombine {
    public static void main(String[] args) {
        try (ExecutorService executor = Executors.newFixedThreadPool(10)) {
            CompletableFuture<String> future1 = CompletableFuture.supplyAsync(
                    () -> {
                        log.info("Educative");
                        return "Educative";
                    },
                    executor);

            CompletableFuture<String> future2 = CompletableFuture.supplyAsync(
                    () -> {
                        log.info("Edpresso");
                        return "Edpresso";
                    },
                    executor);

            CompletableFuture<String> future = future1.thenCombineAsync(future2,
                    (s1, s2) -> {
                        String result = s1 + " : " + s2;
                        log.info("thenCombine: {}", result);
                        return result;
                    },
                    executor);

            log.info("Future 1: {}", future.join());
        }
    }
}
