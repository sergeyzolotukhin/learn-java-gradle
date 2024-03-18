package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringMain {
    public static void main(String[] args) {
        String path = String.join(".", "this", "property", "other");
        log.info("result: {}", path);
    }
}
