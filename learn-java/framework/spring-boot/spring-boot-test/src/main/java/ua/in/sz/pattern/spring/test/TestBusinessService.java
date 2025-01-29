package ua.in.sz.pattern.spring.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestBusinessService {

    String hello() {
        return "Hello world!";
    }
}
