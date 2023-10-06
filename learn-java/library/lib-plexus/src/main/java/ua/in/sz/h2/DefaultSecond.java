package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultSecond implements Second {
    @Override
    public void print() {
        log.info("Hello Default Second");
    }
}
