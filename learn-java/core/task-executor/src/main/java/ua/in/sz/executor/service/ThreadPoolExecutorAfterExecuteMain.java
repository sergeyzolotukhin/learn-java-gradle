package ua.in.sz.executor.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadPoolExecutorAfterExecuteMain {
    private static final ThreadLocal<String> currentTransactionName =
            new ThreadLocal<>();

    public static void main(String[] args) {
        log.info("Start");

        try (ThreadPoolExecutor executor = new LoggedThreadPoolExecutor(
                2, 2,
                100, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(20)
        )) {

            executor.submit(() -> {
                currentTransactionName.set("Task First");
                sleep(1000);
                log.info("Set");
//                    currentTransactionName.remove();
            });

            for (int i = 0; i < 4; i++) {
                executor.submit(() -> {
                    sleep(1000);
                    log.info("Run");
                });
            }

            executor.shutdown();
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        log.info("End");
    }

    @SneakyThrows
    private static void sleep(int timeout) {
        TimeUnit.MILLISECONDS.sleep(timeout);
    }

    public static class LoggedThreadPoolExecutor extends ThreadPoolExecutor {

        public LoggedThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            super.beforeExecute(t, r);

            if (Objects.nonNull(currentTransactionName.get())) {
                log.error("before Execute [{}]", currentTransactionName.get());
            }
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);

            if (Objects.nonNull(currentTransactionName.get())) {
                log.error("After execute [{}]", currentTransactionName.get());
            }
        }
    }

    // TransactionSynchronizationManager
}
