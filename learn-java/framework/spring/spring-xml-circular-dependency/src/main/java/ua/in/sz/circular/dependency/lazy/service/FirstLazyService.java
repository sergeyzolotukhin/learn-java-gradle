package ua.in.sz.circular.dependency.lazy.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class FirstLazyService implements LazyPrintService {
    private LazyPrintService secondService;

    @Override
    public void print() {
        log.info("Hello: {}", secondService.toString());
    }
}
