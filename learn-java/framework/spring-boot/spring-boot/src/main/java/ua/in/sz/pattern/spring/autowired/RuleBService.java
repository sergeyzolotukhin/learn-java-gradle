package ua.in.sz.pattern.spring.autowired;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
@Setter
@Component
public class RuleBService implements RuleService {
    @Override
    public void print() {
        log.info("Hello");
    }
}
