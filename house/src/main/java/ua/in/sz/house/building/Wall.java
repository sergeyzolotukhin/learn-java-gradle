package ua.in.sz.house.building;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * https://bilux.ua/raschet-teplopoter-chastnogo-doma/#
 */
@Slf4j
@AllArgsConstructor
@Builder
public class Wall {

    private final Layout layout;

    private final double length;
    private final double height;

    /**
     * теплопотери, Вт;
     */
    public double getHeatLoss(double tIn, double tOut) {
        return heatTransferRatio() * getSquare() * (tIn - tOut);
    }

    /**
     * Площадь М2
     */
    public double getSquare() {
        return length * height;
    }

    public double getWidth() {
        return layout.getWidth();
    }

    private double heatTransferRatio() {
        double wallThickness = layout.getWidth(); // M
        double heatTransferRatio = layout.getBlock().getHeatTransferRatio(); // Вт/м * C
        double rWall = wallThickness / heatTransferRatio;
        return 1.0 / rWall;
    }

    @Builder
    @Getter
    public static class Layout {
        private Block block;
        private double count;
        private boolean byLength;

        public double getWidth() {
            if (byLength) {
                return count * block.getLength();
            } else {
                return count * block.getWidth();
            }
        }
    }
}
