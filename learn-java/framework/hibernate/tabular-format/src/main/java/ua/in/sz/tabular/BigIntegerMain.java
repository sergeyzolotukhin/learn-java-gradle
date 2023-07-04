package ua.in.sz.tabular;

public class BigIntegerMain {
    public static void main(String[] args) {

    }

    // bitsPerDigit in the given radix times 1024
    // Rounded up to avoid underallocation.
    private static final long bitsPerDigit = 3402;

    static final long LONG_MASK = 0xffffffffL;

    static int[] mag;

    // mag[0] * 1 000 000 000 ^ 0 + mag[1] * 1 000 000 000 ^ 1 + ... + mag[n] * 1 000 000 000 ^ n
    public static void BigInteger(String val, int radix) {
        int cursor = 0, numDigits;
        final int len = val.length();
        numDigits = len - cursor;

        // Pre-allocate array of expected size. May be too large but can
        // never be too small. Typically exact.
        long numBits = ((numDigits * bitsPerDigit) >>> 10) + 1;
        int numWords = (int) (numBits + 31) >>> 5;
        int[] magnitude = new int[numWords];

        // Process first (potentially short) digit group
        int firstGroupLen = numDigits % 9 /*digitsPerInt*/;
        if (firstGroupLen == 0)
            firstGroupLen = 9;

        String group = val.substring(cursor, cursor += firstGroupLen);
        magnitude[numWords - 1] = Integer.parseInt(group, radix);

        // Process remaining digit groups
        int superRadix = 0x3b9aca00; // 1 000 000 000
        int groupVal = 0;
        while (cursor < len) {
            group = val.substring(cursor, cursor += 9);
            groupVal = Integer.parseInt(group, radix);
            destructiveMulAdd(magnitude, superRadix, groupVal);
        }

        mag = magnitude;
    }

    // Multiply x array times word y in place, and add word z
    private static void destructiveMulAdd(int[] x, int superRadix, int groupVal) {
        // Perform the multiplication word by word
        long superRadixlong = superRadix & LONG_MASK;
        long groupVallong = groupVal & LONG_MASK;
        int len = x.length;

        long product = 0;
        long carry = 0;
        for (int i = len-1; i >= 0; i--) {
            product = superRadixlong * (x[i] & LONG_MASK) + carry;
            x[i] = (int)product;
            carry = product >>> 32;
        }

        // Perform the addition
        long sum = (x[len-1] & LONG_MASK) + groupVallong;
        x[len-1] = (int)sum;
        carry = sum >>> 32;
        for (int i = len-2; i >= 0; i--) {
            sum = (x[i] & LONG_MASK) + carry;
            x[i] = (int)sum;
            carry = sum >>> 32;
        }
    }
}
