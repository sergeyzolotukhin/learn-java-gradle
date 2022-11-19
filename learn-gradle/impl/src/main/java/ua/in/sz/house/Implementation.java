package ua.in.sz.house;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Implementation implements Interface {
    @Override
    public void print() {
        log.info("Print message");
    }
}
