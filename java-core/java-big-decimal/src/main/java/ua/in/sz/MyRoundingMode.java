package ua.in.sz;

public enum MyRoundingMode {
    HALF_UP(MyBigDecimal.ROUND_HALF_UP);
    final int oldMode;

    private MyRoundingMode(int oldMode) {
        this.oldMode = oldMode;
    }
}
