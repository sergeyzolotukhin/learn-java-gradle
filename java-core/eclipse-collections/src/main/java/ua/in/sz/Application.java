package ua.in.sz;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * https://www.eclipse.org/collections/
 */
@Slf4j
public class Application {
    public static void main(String[] args) {
        BigDecimal val = new BigDecimal("1.1");
        log.info("{}", val);

        BigInteger i = new BigInteger("123456");
        log.info("{}", i);

        long numBits = 1;
        System.out.println(numBits);
    }
}
