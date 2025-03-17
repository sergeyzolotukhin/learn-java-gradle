package ua.in.sz.circular.dependency.lazy.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class SecondLazyService implements LazyPrintService {
    private LazyPrintService firstService;

    @Override
    public void print() {
        log.info("Hello: {}", firstService.toString());
    }
}
