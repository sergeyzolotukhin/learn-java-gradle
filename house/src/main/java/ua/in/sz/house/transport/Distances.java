package ua.in.sz.house.transport;

import lombok.AllArgsConstructor;
import lombok.Getter;

@SuppressWarnings("SpellCheckingInspection")
public class Distances {

    public static Distance brickStockToVorzel() {
        return Distance.of(26.8, 33.6, 26.8);
    }

    public static Distance brickStockToTarasovo() {
        return Distance.of(49.8, 33.6, 40.2);
    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    public static class Distance {
        private final double comeInDistance;
        private final double comeOutDistance;
        private final double travelDistance;
    }
}
