package ua.in.sz.house.building;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;

/**
 * https://bilux.ua/raschet-teplopoter-chastnogo-doma/#
 */
@Slf4j
@Builder
public class Wall {
    private final Block block;

    private final double height;
    private final double length;

    public Wall(Block block, double height, double length) {
        this.block = block;
        this.height = height;
        this.length = length;
    }

    public double getWidth() {
        if (Block.CERAMICS_BRICK.equals(block)) {
            return 2.0 * block.getLength();
        } else {
            return block.getWidth();
        }
    }

    public double blockCount() {
        if (Block.CERAMICS_BRICK.equals(block)) {
            double cementThickness = 10.0 / 1000.0; // 1 cm
            double countByLength = Math.ceil(length / (block.getLength() + cementThickness));
            double countByHeight = Math.ceil(height / (block.getHeight() + cementThickness));
            double countByWidth = 4.0;
            return countByLength * countByHeight * countByWidth;
        } else {
            throw new NotImplementedException("A block calculation not implemented");
        }
    }

    /**
     * Площадь М2
     */
    public double getSquare() {
        return length * height;
    }

    /**
     * теплопотери, Вт;
     */
    public double getHeatLoss(double tIn, double tOut) {
        return heatTransferRatio() * getSquare() * (tIn - tOut);
    }

    private double heatTransferRatio() {
        double heatTransferRatio = block.getHeatTransferRatio(); // Вт/м * C
        double rWall = getWidth() / heatTransferRatio;
        return 1.0 / rWall;
    }
}
