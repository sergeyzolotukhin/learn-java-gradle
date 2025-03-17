package ua.in.sz.circular.dependency.lookup.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class FirstService implements PrintService {
    @Override
    public void print() {
        log.info("Hello");
    }
}
