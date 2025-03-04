package ua.in.sz.executor.service;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
public class SameThreadPoolExecutorMain {
    public static void main(String[] args) {
        try (ListeningExecutorService executorService = MoreExecutors.newDirectExecutorService()) {

            List<Future<?>> list = new ArrayList<>();

            log.info("submitting");

            for (int i = 0; i < 6; i++) {
                final int no = i;
                try {
                    Future<?> future = executorService.submit(new Runnable() {
                        @SneakyThrows
                        @Override
                        public void run() {
                            log.info("Running {}", no);
                            TimeUnit.SECONDS.sleep(1);
                            log.info("Ran {}", no);
                        }
                    });
                    list.add(future);
                } catch (RejectedExecutionException r) {
                    log.info("Rejected {}", no);
                }
            }

            log.info("submitted");

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

            executorService.shutdown();
        }
    }
}
