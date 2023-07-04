package ua.in.sz;

import java.math.MathContext;
import java.math.RoundingMode;

public class MyMathContext {
    public static final MyMathContext UNLIMITED =
            new MyMathContext(0, MyRoundingMode.HALF_UP);
    private static final int MIN_DIGITS = 0;

    final int precision;

    final MyRoundingMode roundingMode;

    public MyMathContext(int setPrecision, MyRoundingMode setRoundingMode) {
        if (setPrecision < MIN_DIGITS)
            throw new IllegalArgumentException("Digits < 0");
        if (setRoundingMode == null)
            throw new NullPointerException("null RoundingMode");

        precision = setPrecision;
        roundingMode = setRoundingMode;
        return;
    }
}
