package ua.in.sz.junit5;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        log.info("Hello world!");
    }

    public static void throwException() {
        throw new IllegalStateException("My exception");
    }
}