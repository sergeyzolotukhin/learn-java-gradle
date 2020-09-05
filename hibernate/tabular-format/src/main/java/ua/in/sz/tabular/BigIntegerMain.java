package ua.in.sz.tabular;

public class BigIntegerMain {
    public static void main(String[] args) {

    }

    // bitsPerDigit in the given radix times 1024
    // Rounded up to avoid underallocation.
    private static final long bitsPerDigit[] = {
            0, 0, 1024, 1624, 2048, 2378, 2648, 2875, 3072, 3247,

            3402,

            3543, 3672,
            3790, 3899, 4001, 4096, 4186, 4271, 4350, 4426, 4498, 4567, 4633,
            4696, 4756, 4814, 4870, 4923, 4975, 5025, 5074, 5120, 5166, 5210,
            5253, 5295};

    static final long LONG_MASK = 0xffffffffL;

    static int[] mag;

    public static void BigInteger(String val, int radix) {
        int cursor = 0, numDigits;
        final int len = val.length();
        numDigits = len - cursor;

        // Pre-allocate array of expected size. May be too large but can
        // never be too small. Typically exact.
        long numBits = ((numDigits * bitsPerDigit[radix]) >>> 10) + 1;
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
