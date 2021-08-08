package ua.in.sz.house.building;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

/**
 * https://bilux.ua/raschet-teplopoter-chastnogo-doma/#
 */
@Slf4j
@AllArgsConstructor
@Builder
public class Wall {
    private final Block block;

    private final double height;
    private final double length;
    private final double width;

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
        return width;
    }

    private double heatTransferRatio() {
        double wallThickness = width; // M
        double heatTransferRatio = block.getHeatTransferRatio(); // Вт/м * C
        double rWall = wallThickness / heatTransferRatio;
        return 1.0 / rWall;
    }
}
