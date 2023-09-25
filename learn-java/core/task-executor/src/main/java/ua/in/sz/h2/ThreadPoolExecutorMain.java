package ua.in.sz.h2;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class ThreadPoolExecutorMain {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                1, 2,
                10, TimeUnit.SECONDS,
                new ArrayBlockingQueue(2));

        List<Future<?>> list = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            final int no = i;
            try {
                executor.execute(() -> { });
//                executor.invokeAny(Collections.emptyList());
//                executor.invokeAll(Collections.emptyList());
                Future<?> future = executor.submit(new Runnable() {
                    @SneakyThrows
                    @Override
                    public void run() {
                        log.info("Run {}", no);
                        TimeUnit.SECONDS.sleep(no);
                        log.info("Ran {}", no);
                    }
                });
                list.add(future);
            } catch (RejectedExecutionException r) {
                log.info("Rejected {}", no);
            }
        }

        for (Future<?> f : list) {
            try {
                f.get(2, TimeUnit.SECONDS);
            } catch (ExecutionException e) {
                log.info("Execution exception");
            } catch (InterruptedException e) {
                log.info("Interrupted exception");
            } catch (TimeoutException e) {
                log.info("Timeout exception");
            }
        }

        log.info("End");

        executor.shutdown();
    }
}
