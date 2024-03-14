package ua.in.sz.h2.impl;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.h2.Second;

@Slf4j
public class OtherDefaultSecond implements Second {
    @Override
    public void print() {
        log.info("Hello Other Default Second");
    }
}
