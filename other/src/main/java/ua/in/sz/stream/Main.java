package ua.in.sz.stream;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.Stream;

@Slf4j
public class Main {
    public static void main(String[] args) {
        Stream.of("A", "B", "C", "D")
                .forEach(e -> log.info("element: {}", e));
    }
}
