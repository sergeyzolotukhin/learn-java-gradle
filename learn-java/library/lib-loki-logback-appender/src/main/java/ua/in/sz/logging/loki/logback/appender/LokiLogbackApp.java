package ua.in.sz.logging.loki.logback.appender;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
public class LokiLogbackApp {

    @SneakyThrows
    public static void main(String[] args) {
        for (int j = 0; j < 100; j++) {
            for (int i = 0; i < 1000; i++) {
                log.info("Hello World! {}", UUID.randomUUID());

                if (i % 10 == 0) {
                    log.warn("Hello Serhij! {}", UUID.randomUUID());
                }

                TimeUnit.MICROSECONDS.sleep(50);
            }

            log.error("Hello Serhij! {}", UUID.randomUUID());
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
