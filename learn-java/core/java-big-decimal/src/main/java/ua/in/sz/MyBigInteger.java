package ua.in.sz;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class MyBigInteger {
    private static final long[] bitsPerDigit = { 0, 0,
            1024, 1624, 2048, 2378, 2648, 2875, 3072, 3247, 3402 /*10*/, 3543, 3672,
            3790, 3899, 4001, 4096, 4186, 4271, 4350, 4426, 4498, 4567, 4633,
            4696, 4756, 4814, 4870, 4923, 4975, 5025, 5074, 5120, 5166, 5210,
            5253, 5295};
    private static final int[] digitsPerInt = {0, 0,
            30, 19, 15, 13, 11, 11, 10, 9, 9 /* 10 */, 8, 8,
            8, 8, 7, 7, 7, 7, 7, 7, 7, 6, 6, 6, 6,
            6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 5};
    private static final int[] intRadix = {0, 0,
            0x40000000, 0x4546b3db, 0x40000000, 0x48c27395, 0x159fd800,
            0x75db9c97, 0x40000000, 0x17179149, 0x3b9aca00 /* 10 */, 0xcc6db61,
            0x19a10000, 0x309f1021, 0x57f6c100, 0xa2f1b6f,  0x10000000,
            0x18754571, 0x247dbc80, 0x3547667b, 0x4c4b4000, 0x6b5a6e1d,
            0x6c20a40,  0x8d2d931,  0xb640000,  0xe8d4a51,  0x1269ae40,
            0x17179149, 0x1cb91000, 0x23744899, 0x2b73a840, 0x34e63b41,
            0x40000000, 0x4cfa3cc1, 0x5c13d840, 0x6d91b519, 0x39aa400
    };
    private static final int MAX_MAG_LENGTH = Integer.MAX_VALUE / Integer.SIZE + 1; // (1 << 26)
    static final long LONG_MASK = 0xffffffffL;
    public static final MyBigInteger ZERO = new MyBigInteger(new int[0], 0);

    final int signum;
    final int[] mag;

    MyBigInteger(int[] magnitude, int signum) {
        this.signum = (magnitude.length == 0 ? 0 : signum);
        this.mag = magnitude;
        if (mag.length >= MAX_MAG_LENGTH) {
            checkRange();
        }
    }

    public MyBigInteger(String val) {
        this(val, 10);
    }

    public MyBigInteger(String val, int radix) {
        int cursor = 0, numDigits;
        final int len = val.length();

        // Check for at most one leading sign
        int sign = 1;
        int index1 = val.lastIndexOf('-');
        int index2 = val.lastIndexOf('+');
        if (index1 >= 0) {
            sign = -1;
            cursor = 1;
        } else if (index2 >= 0) {
            cursor = 1;
        }

        // Skip leading zeros and compute number of digits in magnitude
        while (cursor < len && Character.digit(val.charAt(cursor), radix) == 0) {
            cursor++;
        }

        if (cursor == len) {
            signum = 0;
            mag = ZERO.mag;
            return;
        }

        numDigits = len - cursor;
        signum = sign;

        // Pre-allocate array of expected size. May be too large but can never be too small. Typically exact.
        long numBits = ((numDigits * bitsPerDigit[radix]) >>> 10) + 1;
        if (numBits + 31 >= (1L << 32)) {
            reportOverflow();
        }
        int numWords = (int) (numBits + 31) >>> 5;
        int[] magnitude = new int[numWords];

        // Process first (potentially short) digit group
        int firstGroupLen = numDigits % digitsPerInt[radix];
        if (firstGroupLen == 0)
            firstGroupLen = digitsPerInt[radix];
        String group = val.substring(cursor, cursor += firstGroupLen);
        magnitude[numWords - 1] = Integer.parseInt(group, radix);
        if (magnitude[numWords - 1] < 0)
            throw new NumberFormatException("Illegal digit");
        log.info("G: {}", group);
        log.info("M[{}]: {}", numWords - 1, magnitude[numWords - 1]);

        // Process remaining digit groups
        int superRadix = intRadix[radix];
        int groupVal = 0;
        while (cursor < len) {
            group = val.substring(cursor, cursor += digitsPerInt[radix]);
            groupVal = Integer.parseInt(group, radix);
            if (groupVal < 0)
                throw new NumberFormatException("Illegal digit");

            log.info("G: {}", group);
            destructiveMulAdd(magnitude, superRadix, groupVal);
        }

        // Required for cases where the array was overallocated.
        mag = trustedStripLeadingZeroInts(magnitude);
        if (mag.length >= MAX_MAG_LENGTH) {
            checkRange();
        }
    }

    private static void destructiveMulAdd(int[] x, int superRadix, int groupVal) {
        // Perform the multiplication word by word
        long ysuperRadix = superRadix & LONG_MASK;
        long zgroupVal = groupVal & LONG_MASK;
        int len = x.length;

        long product = 0;
        long carry = 0;
        for (int i = len-1; i >= 0; i--) {
            product = ysuperRadix * (x[i] & LONG_MASK) + carry;
            log.info("M[{}]: {} <= superRadix {} * x[{}] {} + carry {}", i, product, superRadix, i, x[i], carry);
            x[i] = (int)product;
            carry = product >>> 32;
        }

        // Perform the addition
        long sum = (x[len-1] & LONG_MASK) + zgroupVal;
        x[len-1] = (int)sum;
        carry = sum >>> 32;
        for (int i = len-2; i >= 0; i--) {
            sum = (x[i] & LONG_MASK) + carry;
            x[i] = (int)sum;
            log.info("S[{}]: {} <= superRadix {} groupVal {}",i, sum, superRadix, groupVal);
            carry = sum >>> 32;
        }
    }

    private static int[] trustedStripLeadingZeroInts(int[] val) {
        int vlen = val.length;
        int keep;

        // Find first nonzero byte
        for (keep = 0; keep < vlen && val[keep] == 0; keep++)
            ;
        return keep == 0 ? val : java.util.Arrays.copyOfRange(val, keep, vlen);
    }

    private void checkRange() {
        if (mag.length > MAX_MAG_LENGTH || mag.length == MAX_MAG_LENGTH && mag[0] < 0) {
            reportOverflow();
        }
    }

    private static void reportOverflow() {
        throw new ArithmeticException("BigInteger would overflow supported range");
    }

    @Override
    public String toString() {
        String sb = "MyBigInteger{" + "signum=" + signum +
                ", mag=" + Arrays.toString(mag) +
                '}';
        return sb;
    }
}
