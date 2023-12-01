package ua.in.sz.h2.plugin;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomBussinessPlugin implements BussinessPlugin {
    @Override
    public void print() {
        log.info("Hello");
    }
}
