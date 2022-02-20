package ua.in.sz;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.BigInteger;

@Slf4j
public class Application {
    private static long bitsPerDigit[] = { 0, 0,
            1024, 1624, 2048, 2378, 2648, 2875, 3072, 3247, 3402 /*10*/, 3543, 3672,
            3790, 3899, 4001, 4096, 4186, 4271, 4350, 4426, 4498, 4567, 4633,
            4696, 4756, 4814, 4870, 4923, 4975, 5025, 5074, 5120, 5166, 5210,
            5253, 5295};

    public static void main(String[] args) {
        BigDecimal val = new BigDecimal("1.1");
        MyBigDecimal val1 = new MyBigDecimal("1000.001001001001");
        log.info("{}", val1);

        BigInteger i = new BigInteger("123456");
        MyBigInteger i1 = new MyBigInteger("100101" + "100100102" + "100100103");
        log.info("{}", i1);

        int radix = 10;
        int numDigits = 4;

        long numBits = ((numDigits * bitsPerDigit[radix]) >>> 10) + 1;
        System.out.println(numBits);
    }
}
