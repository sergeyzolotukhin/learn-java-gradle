package ua.in.sz.executor.service.future;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
public class CompletableFutureThenCompose {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        log.info("Starting app");

        try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
            CompletableFuture<String> future1 = CompletableFuture.supplyAsync(new FirstTask(), executor);

            CompletableFuture<String> future2 = future1.thenComposeAsync(
                    (Function<String, CompletableFuture<String>>) s -> CompletableFuture.supplyAsync(
                            new SecondTask(s),
                            executor),
                    executor);

            log.info("Submitted");

//            sleep(6);
            String result = future2.get();

            log.info("Done {}, Canceled {}, state {}, result {}",
                    future2.isDone(), future2.isCancelled(), future2.state(), result);

        }

        log.info("Ended app");
    }

    static class FirstTask implements Supplier<String> {
        @Override
        @SneakyThrows
        public String get() {
            log.info("Starting FirstTask");
            sleep(2);
            log.info("Ended FirstTask");
            return "FirstTask";
        }
    }

    static record SecondTask(String s) implements Supplier<String> {
        @Override
        @SneakyThrows
        public String get() {
            String result = "SecondTask " + s;
            log.info("Starting: {}", result);
            sleep(2);
            log.info("Ended: {}", result);
            return result;
        }
    }

    @SneakyThrows
    private static void sleep(long timeout) {
        TimeUnit.SECONDS.sleep(timeout);
    }
}
