package ua.in.sz;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.BigInteger;

@Slf4j
public class Application {
    public static void main(String[] args) {
        BigDecimal val = new BigDecimal("1.1");
        MyBigDecimal val1 = new MyBigDecimal("1000.001001001001");
        log.info("{}", val1);

        BigInteger i = new BigInteger("123456");
        MyBigInteger i1 = new MyBigInteger("100100" + "100100" + "100100" + "100100");
        log.info("{}", i1);
    }
}
