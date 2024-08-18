package ua.in.sz.logging.diff.traget;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DiffTargetApp {
    public static void main(String[] args) {
        log.info("Hello World");

        OtherComponent.main(null);
    }
}
