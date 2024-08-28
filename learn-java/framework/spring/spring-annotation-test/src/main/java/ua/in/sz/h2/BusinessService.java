package ua.in.sz.h2;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
@Setter
@Component
public class BusinessService {
    void print() {
        log.info("Hello World!");
    }
}
