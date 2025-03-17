package ua.in.sz.circular.dependency.lookup.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@RequiredArgsConstructor
public class ThirdService implements PrintService {
    private final PrintService printService;

    @Override
    public void print() {
        log.info("Hello: {}", printService.toString());
        printService.print();
    }
}
