package ua.in.sz.pattern.spring.autowired;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
@Setter
//@Component
@AllArgsConstructor
@NoArgsConstructor
public class RuleAService implements RuleService {
    private String name = "unnamed";

    @Override
    public void print() {
        log.info("Hello {}", name);
    }
}
