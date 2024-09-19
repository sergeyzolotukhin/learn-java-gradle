package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadPoolExecutorAfterExecuteMain {
    public static void main(String[] args) {
        log.info("Start");

        try (ThreadPoolExecutor executor = new ThreadPoolExecutor(
                1, 2,
                10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(20)
        )) {

            for (int i = 0; i < 4; i++) {
                executor.submit(new Runnable() {
                    @Override
                    public void run() {
                        log.info("Run");
                    }
                });
            }

            executor.awaitTermination(10, TimeUnit.SECONDS);
            executor.shutdown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        log.info("End");
    }
}
