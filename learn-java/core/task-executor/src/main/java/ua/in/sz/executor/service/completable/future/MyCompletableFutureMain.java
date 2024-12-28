package ua.in.sz.executor.service.completable.future;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

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
        log.info("Starting app");

        try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
            MyCompletableFuture<String> future1 = MyCompletableFuture.supplyAsync(new FirstTask(), executor);

            MyCompletableFuture<String> future2 = future1.thenComposeAsync(
                    (Function<String, MyCompletableFuture<String>>) s -> MyCompletableFuture.supplyAsync(
                            new SecondTask(s),
                            executor),
                    executor);

            log.info("Submitted");

            String result = future2.get();

            log.info("Done {}, Canceled {}, state {}, result {}",
                    future1.isDone(), future1.isCancelled(), future1.state(), result);

        }

        log.info("Ended app");
    }

    static class FirstTask implements Supplier<String> {
        @Override
        @SneakyThrows
        public String get() {
            log.info("Starting FirstTask");
//            sleep(2);
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
