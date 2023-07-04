package ua.in.sz.pattern.gof.proxy.impl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RealSubject implements Subject {
    @Override
    public String execute(String name) {
        log.info("Execute real subject with parameter: {}", name);
        return String.format("Hi %s", name);
    }
}
