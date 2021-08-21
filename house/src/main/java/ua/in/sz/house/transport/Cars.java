package ua.in.sz.house.transport;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class Cars {

    public static DafCf65 dafCf65() {
        return new DafCf65(7.2, 2.5, 2.5, 10_000);
    }

    @Getter
    @AllArgsConstructor
    public static class DafCf65 {
        private final double length;
        private final double width;
        private final double height;

        private final double maxWeight;
    }
}
