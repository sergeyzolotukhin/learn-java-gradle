package ua.in.sz.circular.dependency.lookup.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@RequiredArgsConstructor
public class SecondService implements PrintService {

    private final PrintService firstService;

    @Override
    public void print() {
        log.info("Hello: {}", thirdService().toString());
        log.info("Hello: {}", firstService.toString());
    }

    public PrintService thirdService() {
        throw new IllegalStateException();
    }
}
