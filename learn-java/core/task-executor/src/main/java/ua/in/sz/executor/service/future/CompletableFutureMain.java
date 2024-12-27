package ua.in.sz.executor.service.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

@Slf4j
public class CompletableFutureMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        log.info("Starting");

        try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
            CompletableFuture<String> future = CompletableFuture.supplyAsync(CompletableFutureMain::getSupplyAsync, executor);
            log.info("Submitted");

            log.info("Result: [{}]", future.get());
        }

        log.info("Ended");

    }

    private static String getSupplyAsync() {
        log.info("SupplyAsync");
        return "Hello World";
    }
}
