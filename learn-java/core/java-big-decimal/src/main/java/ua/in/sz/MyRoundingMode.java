package ua.in.sz;

public enum MyRoundingMode {
    HALF_UP(MyBigDecimal.ROUND_HALF_UP);
    final int oldMode;

    MyRoundingMode(int oldMode) {
        this.oldMode = oldMode;
    }
}
