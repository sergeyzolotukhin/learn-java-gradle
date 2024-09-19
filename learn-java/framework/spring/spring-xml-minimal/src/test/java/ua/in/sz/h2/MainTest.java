package ua.in.sz.h2;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class MainTest {

    @Test
    void main() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(10);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.initialize();
        executor.setTaskDecorator(runnable -> () -> {
            log.info("before");
            runnable.run();
            log.info("after");
        });


        executor.submit(() -> {
            log.info("run");
            sleet(100);
        });

        for (int i = 0; i < 5; i++) {
            executor.submit(() -> {
                log.info("run");
                sleet(100);
            });
        }

        sleet(3000);
//        executor.initiateShutdown();
        executor.shutdown();

    }

    @SneakyThrows
    private static void sleet(int timeout) {
        TimeUnit.MILLISECONDS.sleep(timeout);
    }

    // java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy
}