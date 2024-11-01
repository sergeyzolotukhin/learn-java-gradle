package ua.in.sz.pattern.spring.autowired;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Getter
@Setter
//@Component
public class ManagerService {

    private final List<RuleService> rules;

    public ManagerService(List<RuleService> rules) {
        this.rules = rules;
    }

    public void print() {
        log.info("Hello");

        rules.forEach(RuleService::print);
    }
}
