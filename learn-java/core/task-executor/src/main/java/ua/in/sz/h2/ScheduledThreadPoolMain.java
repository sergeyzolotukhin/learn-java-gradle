package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class ScheduledThreadPoolMain {
    public static void main(String[] args) {
        log.info("Start");

        try (ScheduledExecutorService service = Executors.newScheduledThreadPool(2)) {
            List<Future<String>> list = new ArrayList<>();

            for (int i = 0; i < 4; i++) {
                final int no = i;

                Future<String> result = service.schedule(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        log.info("Starting {}", no);
                        TimeUnit.SECONDS.sleep(3);
                        log.info("End {}", no);

                        return String.format("Result: %s", no);
                    }
                }, 5, TimeUnit.SECONDS);
                list.add(result);
            }

            log.info("Submitted");

            for (Future<String> l : list) {
                try {
                    log.info("Info: {}", l.get(3, TimeUnit.SECONDS));
                } catch (TimeoutException e) {
                    log.info("I can not wait a finish");
                }
            }

            log.info("Received");

            service.shutdown();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        log.info("End");
    }
}
