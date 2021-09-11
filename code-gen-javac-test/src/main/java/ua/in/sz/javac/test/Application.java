package ua.in.sz.javac.test;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.javac.Positive;

@Slf4j
public class Application {
    public static void main(String[] args) {
        doExec(10);
    }

    private static void doExec(@Positive int i) {
        log.info("The i has value {}", i);
    }
}
