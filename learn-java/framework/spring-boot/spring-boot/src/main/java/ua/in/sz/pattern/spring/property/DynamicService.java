package ua.in.sz.pattern.spring.property;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
@Setter
public class DynamicService {
    void print() {
        log.info("Hello");
    }
}
