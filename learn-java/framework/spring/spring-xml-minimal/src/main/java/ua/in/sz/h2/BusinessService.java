package ua.in.sz.h2;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class BusinessService implements PrintService {
    @Override
    public void print() {
        log.info("Hello");
    }
}
