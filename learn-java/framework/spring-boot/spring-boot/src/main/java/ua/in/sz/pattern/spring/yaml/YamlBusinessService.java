package ua.in.sz.pattern.spring.yaml;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
@Setter
@Component
public class YamlBusinessService {
    void print() {
        log.info("Hello");
    }
}
