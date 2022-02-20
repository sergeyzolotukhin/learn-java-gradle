package ua.in.sz;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class Application {
    public static void main(String[] args) {
        BigDecimal val = new BigDecimal("1.1");
        MyBigDecimal val1 = new MyBigDecimal("1.1");
        log.info("{}", val1);
    }
}
