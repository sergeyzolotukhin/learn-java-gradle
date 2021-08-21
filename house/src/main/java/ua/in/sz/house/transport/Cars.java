package ua.in.sz.house.transport;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class Cars {

    /**
     * DAF CF 65 - 10 000 Kg
     */
    public static CargoCar dafCf65() {
        return new CargoCar(7.2, 2.5, 2.5, 10_000, 370, 370, 18);
    }

    /**
     * DAF XF 95 - 20 000 Kg
     */
    public static CargoCar dafXf95() {
        return new CargoCar(13.6, 2.5, 2.5, 20_000, 620, 620, 30);
    }

    @Getter
    @AllArgsConstructor
    public static class CargoCar {
        private final double length;
        private final double width;
        private final double height;

        private final double maxWeight;

        private final double comeInCost;
        private final double hourCost;
        private final double kmCost;
    }
}
