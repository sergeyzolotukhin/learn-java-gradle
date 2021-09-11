package ua.in.sz.javacore.optional;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
public class Application {
    public static void main(String[] args) {
        double val = Math.random();

        Optional<BigDecimal> n1 = val > 0.5 ? Optional.of(BigDecimal.valueOf(val)) : Optional.empty();
        Optional<BigDecimal> n2 = val > 0.7 ? Optional.of(BigDecimal.valueOf(val)) : Optional.empty();
        Optional<BigDecimal> n3 = val > 0.8 ? Optional.of(BigDecimal.valueOf(val)) : Optional.empty();

        BigDecimal result = BigDecimal.ZERO;
        if (n1.isPresent() && n2.isPresent() && n3.isPresent()) {
            result = n1.get().add(n2.get()).subtract(n3.get());
        }

        log.info("{}", result);
    }
}
