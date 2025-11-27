package ua.in.sz.logging.loki.logback.appender;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
public class LokiLogbackApp {

    @SneakyThrows
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            log.info("Hello World! {}", UUID.randomUUID());
        }
        TimeUnit.SECONDS.sleep(60);
    }
}
