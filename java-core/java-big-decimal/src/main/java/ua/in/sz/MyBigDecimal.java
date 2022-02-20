package ua.in.sz;

import org.apache.commons.lang3.NotImplementedException;

import java.math.BigInteger;
import java.math.RoundingMode;

public class MyBigDecimal {
    private static final int MAX_COMPACT_DIGITS = 18;

    private static final long[] LONG_TEN_POWERS_TABLE = {
            1,                     // 0 / 10^0
            10,                    // 1 / 10^1
            100,                   // 2 / 10^2
            1000,                  // 3 / 10^3
            10000,                 // 4 / 10^4
            100000,                // 5 / 10^5
            1000000,               // 6 / 10^6
            10000000,              // 7 / 10^7
            100000000,             // 8 / 10^8
            1000000000,            // 9 / 10^9
            10000000000L,          // 10 / 10^10
            100000000000L,         // 11 / 10^11
            1000000000000L,        // 12 / 10^12
            10000000000000L,       // 13 / 10^13
            100000000000000L,      // 14 / 10^14
            1000000000000000L,     // 15 / 10^15
            10000000000000000L,    // 16 / 10^16
            100000000000000000L,   // 17 / 10^17
            1000000000000000000L   // 18 / 10^18
    };
    static final long INFLATED = Long.MIN_VALUE;
    private static final long HALF_LONG_MAX_VALUE = Long.MAX_VALUE / 2;
    private static final long HALF_LONG_MIN_VALUE = Long.MIN_VALUE / 2;
    public static final int ROUND_DOWN = 1;
    public static final int ROUND_UP = 0;
    public static final int ROUND_CEILING = 2;
    public static final int ROUND_FLOOR = 3;
    public static final int ROUND_HALF_UP = 4;
    public static final int ROUND_HALF_DOWN = 5;
    public static final int ROUND_HALF_EVEN = 6;
    public static final int ROUND_UNNECESSARY = 7;

    // state

    private final int scale;
    private transient int precision;
    private final transient long intCompact;
    private final BigInteger intVal;

    public MyBigDecimal(String val) {
        this(val.toCharArray(), 0, val.length());
    }

    public MyBigDecimal(char[] in, int offset, int len) {
        this(in, offset, len, MyMathContext.UNLIMITED);
    }

    public MyBigDecimal(char[] in, int offset, int len, MyMathContext mc) {
        int prec = 0;                 // record precision value
        int scl = 0;                  // record scale value
        long rs = 0;                  // the compact value in long
        BigInteger rb = null;         // the inflated value in BigInteger

        try {
            // handle the sign
            boolean isneg = false;          // assume positive
            if (in[offset] == '-') {
                isneg = true;               // leading minus means negative
                offset++;
                len--;
            } else if (in[offset] == '+') { // leading + allowed
                offset++;
                len--;
            }

            // should now be at numeric part of the significand
            boolean dot = false;             // true when there is a '.'
            char c;                          // current character
            boolean isCompact = (len <= MAX_COMPACT_DIGITS);
            // integer significand array & idx is the index to it. The array
            // is ONLY used when we can't use a compact representation.
            int idx = 0;
            if (isCompact) {
                // First compact case, we need not to preserve the character and we can just compute the value in place.
                for (; len > 0; offset++, len--) {
                    c = in[offset];
                    if ((c == '0')) { // have zero
                        if (prec == 0)
                            prec = 1;
                        else if (rs != 0) {
                            rs *= 10;
                            ++prec;
                        } // else digit is a redundant leading zero
                        if (dot)
                            ++scl;
                    } else if ((c >= '1' && c <= '9')) { // have digit
                        int digit = c - '0';
                        if (prec != 1 || rs != 0)
                            ++prec; // prec unchanged if preceded by 0s
                        rs = rs * 10 + digit;
                        if (dot)
                            ++scl;
                    } else if (c == '.') {   // have dot
                        // have dot
                        if (dot) // two dots
                            throw new NumberFormatException("Character array contains more than one decimal point.");
                        dot = true;
                    } else if (Character.isDigit(c)) { // slow path
                        throw new NotImplementedException("E");
                    } else if ((c == 'e') || (c == 'E')) {
                        throw new NotImplementedException("E");
                    } else {
                        throw new NumberFormatException(
                                "Character " + c + " is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark.");
                    }
                }

                if (prec == 0) // no digits found
                    throw new NumberFormatException("No digits found.");

                rs = isneg ? -rs : rs;
                int mcp = mc.precision;
                int drop = prec - mcp; // prec has range [1, MAX_INT], mcp has range [0, MAX_INT];
                // therefore, this subtract cannot overflow
                if (mcp > 0 && drop > 0) {  // do rounding
                    while (drop > 0) {
                        scl = checkScaleNonZero((long) scl - drop);
                        rs = divideAndRound(rs, LONG_TEN_POWERS_TABLE[drop], mc.roundingMode.oldMode);
                        prec = longDigitLength(rs);
                        drop = prec - mcp;
                    }
                }
            } else {
                throw new NotImplementedException("!isCompact");
            }
        } catch (ArrayIndexOutOfBoundsException | NegativeArraySizeException e) {
            NumberFormatException nfe = new NumberFormatException();
            nfe.initCause(e);
            throw nfe;
        }
        this.scale = scl;
        this.precision = prec;
        this.intCompact = rs;
        this.intVal = rb;
    }

    private static int checkScaleNonZero(long val) {
        int asInt = (int) val;
        if (asInt != val) {
            throw new ArithmeticException(asInt > 0 ? "Underflow" : "Overflow");
        }
        return asInt;
    }

    static int longDigitLength(long x) {
        /*
         * As described in "Bit Twiddling Hacks" by Sean Anderson,
         * (http://graphics.stanford.edu/~seander/bithacks.html)
         * integer log 10 of x is within 1 of (1233/4096)* (1 +
         * integer log 2 of x). The fraction 1233/4096 approximates
         * log10(2). So we first do a version of log2 (a variant of
         * Long class with pre-checks and opposite directionality) and
         * then scale and check against powers table. This is a little
         * simpler in present context than the version in Hacker's
         * Delight sec 11-4. Adding one to bit length allows comparing
         * downward from the LONG_TEN_POWERS_TABLE that we need
         * anyway.
         */
        assert x != INFLATED;
        if (x < 0)
            x = -x;
        if (x < 10) // must screen for 0, might as well 10
            return 1;
        int r = ((64 - Long.numberOfLeadingZeros(x) + 1) * 1233) >>> 12;
        long[] tab = LONG_TEN_POWERS_TABLE;
        // if r >= length, must have max possible digits for long
        return (r >= tab.length || x < tab[r]) ? r : r + 1;
    }

    private static long divideAndRound(long ldividend, long ldivisor, int roundingMode) {
        int qsign; // quotient sign
        long q = ldividend / ldivisor; // store quotient in long
        if (roundingMode == ROUND_DOWN)
            return q;
        long r = ldividend % ldivisor; // store remainder in long
        qsign = ((ldividend < 0) == (ldivisor < 0)) ? 1 : -1;
        if (r != 0) {
            boolean increment = needIncrement(ldivisor, roundingMode, qsign, q, r);
            return increment ? q + qsign : q;
        } else {
            return q;
        }
    }

    private static boolean needIncrement(long ldivisor, int roundingMode,
                                         int qsign, long q, long r) {
        assert r != 0L;

        int cmpFracHalf;
        if (r <= HALF_LONG_MIN_VALUE || r > HALF_LONG_MAX_VALUE) {
            cmpFracHalf = 1; // 2 * r can't fit into long
        } else {
            cmpFracHalf = longCompareMagnitude(2 * r, ldivisor);
        }

        return commonNeedIncrement(roundingMode, qsign, cmpFracHalf, (q & 1L) != 0L);
    }

    private static int longCompareMagnitude(long x, long y) {
        if (x < 0)
            x = -x;
        if (y < 0)
            y = -y;
        return (x < y) ? -1 : ((x == y) ? 0 : 1);
    }

    private static boolean commonNeedIncrement(int roundingMode, int qsign,
                                               int cmpFracHalf, boolean oddQuot) {
        switch (roundingMode) {
            case ROUND_UNNECESSARY:
                throw new ArithmeticException("Rounding necessary");

            case ROUND_UP: // Away from zero
                return true;

            case ROUND_DOWN: // Towards zero
                return false;

            case ROUND_CEILING: // Towards +infinity
                return qsign > 0;

            case ROUND_FLOOR: // Towards -infinity
                return qsign < 0;

            default: // Some kind of half-way rounding
                assert roundingMode >= ROUND_HALF_UP &&
                        roundingMode <= ROUND_HALF_EVEN : "Unexpected rounding mode" + RoundingMode.valueOf(roundingMode);

                if (cmpFracHalf < 0) // We're closer to higher digit
                    return false;
                else if (cmpFracHalf > 0) // We're closer to lower digit
                    return true;
                else { // half-way
                    assert cmpFracHalf == 0;

                    return switch (roundingMode) {
                        case ROUND_HALF_DOWN -> false;
                        case ROUND_HALF_UP -> true;
                        case ROUND_HALF_EVEN -> oddQuot;

                        default -> throw new AssertionError("Unexpected rounding mode" + roundingMode);
                    };
                }
        }
    }

    @Override
    public String toString() {
        return layoutChars(true);
    }

    public int signum() {
        return (intCompact != INFLATED) ?
                Long.signum(intCompact) :
                intVal.signum();
    }

    private String layoutChars(boolean sci) {
        if (scale == 0)                      // zero scale is trivial
            return (intCompact != INFLATED) ?
                    Long.toString(intCompact) :
                    intVal.toString();
        if (scale == 2 &&
                intCompact >= 0 && intCompact < Integer.MAX_VALUE) {
            // currency fast path
            int lowInt = (int) intCompact % 100;
            int highInt = (int) intCompact / 100;
            return (Integer.toString(highInt) + '.' +
                    MyStringBuilderHelper.DIGIT_TENS[lowInt] +
                    MyStringBuilderHelper.DIGIT_ONES[lowInt]);
        }

        MyStringBuilderHelper sbHelper = new MyStringBuilderHelper();
        char[] coeff;
        int offset;  // offset is the starting index for coeff array
        // Get the significand as an absolute value
        if (intCompact != INFLATED) {
            offset = sbHelper.putIntCompact(Math.abs(intCompact));
            coeff = sbHelper.getCompactCharArray();
        } else {
            offset = 0;
            coeff = intVal.abs().toString().toCharArray();
        }

        // Construct a buffer, with sufficient capacity for all cases.
        // If E-notation is needed, length will be: +1 if negative, +1
        // if '.' needed, +2 for "E+", + up to 10 for adjusted exponent.
        // Otherwise it could have +1 if negative, plus leading "0.00000"
        StringBuilder buf = sbHelper.getStringBuilder();
        if (signum() < 0)             // prefix '-' if negative
            buf.append('-');
        int coeffLen = coeff.length - offset;
        long adjusted = -(long) scale + (coeffLen - 1);
        if ((scale >= 0) && (adjusted >= -6)) { // plain number
            int pad = scale - coeffLen;         // count of padding zeros
            if (pad >= 0) {                     // 0.xxx form
                buf.append('0');
                buf.append('.');
                for (; pad > 0; pad--) {
                    buf.append('0');
                }
                buf.append(coeff, offset, coeffLen);
            } else {                         // xx.xx form
                buf.append(coeff, offset, -pad);
                buf.append('.');
                buf.append(coeff, -pad + offset, scale);
            }
        } else { // E-notation is needed
            if (sci) {                       // Scientific notation
                buf.append(coeff[offset]);   // first character
                if (coeffLen > 1) {          // more to come
                    buf.append('.');
                    buf.append(coeff, offset + 1, coeffLen - 1);
                }
            } else {                         // Engineering notation
                int sig = (int) (adjusted % 3);
                if (sig < 0)
                    sig += 3;                // [adjusted was negative]
                adjusted -= sig;             // now a multiple of 3
                sig++;
                if (signum() == 0) {
                    switch (sig) {
                        case 1:
                            buf.append('0'); // exponent is a multiple of three
                            break;
                        case 2:
                            buf.append("0.00");
                            adjusted += 3;
                            break;
                        case 3:
                            buf.append("0.0");
                            adjusted += 3;
                            break;
                        default:
                            throw new AssertionError("Unexpected sig value " + sig);
                    }
                } else if (sig >= coeffLen) {   // significand all in integer
                    buf.append(coeff, offset, coeffLen);
                    // may need some zeros, too
                    for (int i = sig - coeffLen; i > 0; i--) {
                        buf.append('0');
                    }
                } else {                     // xx.xxE form
                    buf.append(coeff, offset, sig);
                    buf.append('.');
                    buf.append(coeff, offset + sig, coeffLen - sig);
                }
            }
            if (adjusted != 0) {             // [!sci could have made 0]
                buf.append('E');
                if (adjusted > 0)            // force sign for positive
                    buf.append('+');
                buf.append(adjusted);
            }
        }
        return buf.toString();
    }

    static class MyStringBuilderHelper {
        final StringBuilder sb;    // Placeholder for BigDecimal string
        final char[] cmpCharArray; // character array to place the intCompact

        MyStringBuilderHelper() {
            sb = new StringBuilder(32);
            // All non negative longs can be made to fit into 19 character array.
            cmpCharArray = new char[19];
        }

        // Accessors.
        StringBuilder getStringBuilder() {
            sb.setLength(0);
            return sb;
        }

        char[] getCompactCharArray() {
            return cmpCharArray;
        }

        /**
         * Places characters representing the intCompact in {@code long} into
         * cmpCharArray and returns the offset to the array where the
         * representation starts.
         *
         * @param intCompact the number to put into the cmpCharArray.
         * @return offset to the array where the representation starts.
         * Note: intCompact must be greater or equal to zero.
         */
        int putIntCompact(long intCompact) {
            assert intCompact >= 0;

            long q;
            int r;
            // since we start from the least significant digit, charPos points to
            // the last character in cmpCharArray.
            int charPos = cmpCharArray.length;

            // Get 2 digits/iteration using longs until quotient fits into an int
            while (intCompact > Integer.MAX_VALUE) {
                q = intCompact / 100;
                r = (int) (intCompact - q * 100);
                intCompact = q;
                cmpCharArray[--charPos] = DIGIT_ONES[r];
                cmpCharArray[--charPos] = DIGIT_TENS[r];
            }

            // Get 2 digits/iteration using ints when i2 >= 100
            int q2;
            int i2 = (int) intCompact;
            while (i2 >= 100) {
                q2 = i2 / 100;
                r = i2 - q2 * 100;
                i2 = q2;
                cmpCharArray[--charPos] = DIGIT_ONES[r];
                cmpCharArray[--charPos] = DIGIT_TENS[r];
            }

            cmpCharArray[--charPos] = DIGIT_ONES[i2];
            if (i2 >= 10)
                cmpCharArray[--charPos] = DIGIT_TENS[i2];

            return charPos;
        }

        static final char[] DIGIT_TENS = {
                '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
                '1', '1', '1', '1', '1', '1', '1', '1', '1', '1',
                '2', '2', '2', '2', '2', '2', '2', '2', '2', '2',
                '3', '3', '3', '3', '3', '3', '3', '3', '3', '3',
                '4', '4', '4', '4', '4', '4', '4', '4', '4', '4',
                '5', '5', '5', '5', '5', '5', '5', '5', '5', '5',
                '6', '6', '6', '6', '6', '6', '6', '6', '6', '6',
                '7', '7', '7', '7', '7', '7', '7', '7', '7', '7',
                '8', '8', '8', '8', '8', '8', '8', '8', '8', '8',
                '9', '9', '9', '9', '9', '9', '9', '9', '9', '9',
        };

        static final char[] DIGIT_ONES = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        };
    }
}
