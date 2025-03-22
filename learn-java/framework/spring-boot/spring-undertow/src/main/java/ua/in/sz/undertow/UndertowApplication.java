package ua.in.sz.undertow;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class UndertowApplication {
    public static void main(String[] args) {
        SpringApplication.run(UndertowApplication.class, args);
        log.info("Hello world!");
    }
}