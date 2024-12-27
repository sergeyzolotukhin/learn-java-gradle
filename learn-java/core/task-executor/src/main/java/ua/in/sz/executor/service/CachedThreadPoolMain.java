package ua.in.sz.executor.service;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class CachedThreadPoolMain {
    public static void main(String[] args) {
        log.info("Start");

        try (ExecutorService service = Executors.newCachedThreadPool()) {
            List<Future<String>> list = new ArrayList<>();

            for (int i = 0; i < 4; i++) {
                final int no = i;

                Future<String> result = service.submit(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        log.info("Starting {}", no);
                        TimeUnit.SECONDS.sleep(3);
                        log.info("End {}", no);

                        return String.format("Result: %s", no);
                    }
                });
                list.add(result);
            }

            log.info("Submitted");

            for (Future<String> l : list) {
                log.info("Info: {}", l.get());
            }

            log.info("Received");

            service.shutdown();
        } catch (ExecutionException e) {
            log.info("Execution exception");
        } catch (InterruptedException e) {
            log.info("Interrupted exception");
        }

        log.info("End");
    }
}
