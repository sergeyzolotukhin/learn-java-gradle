package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Tool {
    public void print() {
        log.info("Hello {}", this);
    }
}
