package ua.in.sz.house.building;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;

/**
 * https://bilux.ua/raschet-teplopoter-chastnogo-doma/#
 */
@Slf4j
@Builder
public class Wall {
    private static final double CEMENT_THICKNESS = 10.0 / 1000.0; // 1 cm

    private final Block block;
    @Getter
    private final double height;
    @Getter
    private final double length;

    public Wall(Block block, double height, double length) {
        this.block = block;
        this.height = height;
        this.length = length;
    }

    public double getWidth() {
        if (Block.CERAMICS_BRICK.equals(block)) {
            return 2.0 * block.getLength() + CEMENT_THICKNESS;
        } else {
            return block.getWidth();
        }
    }

    public double blockCount() {
        if (Block.CERAMICS_BRICK.equals(block)) {

            double countByLength = Math.ceil(length / (block.getLength() + CEMENT_THICKNESS));
            double countByHeight = Math.ceil(height / (block.getHeight() + CEMENT_THICKNESS));
            double countByWidth = 4.0;
            return countByLength * countByHeight * countByWidth;
        } else {
            throw new NotImplementedException("A block calculation not implemented");
        }
    }

    /**
     * Объем цементно песчаного раствора М3
     */
    public double cementMortar() {
        if (Block.CERAMICS_BRICK.equals(block)) {
            int m3ToMm3 = 1_000_000_000;
            int mToMm = 1_000;

            double countByHeight = Math.ceil(height / (block.getHeight() + CEMENT_THICKNESS));
            double countByLength = Math.ceil(length / (block.getLength() + CEMENT_THICKNESS));
            double countByWidth = 4.0;

            double sliceVolume = CEMENT_THICKNESS * block.getHeight() * block.getWidth();
            double sliceVolumePerRow = (countByLength - 1.0) * sliceVolume;
            double sliceVolumePerLayer = sliceVolumePerRow * (countByWidth - 1.0);

            log.debug(String.format("slice cement mortar one %.0f mm3, row %.0f mm3, layer %.0f mm3. Block count %.2f",
                    sliceVolume * m3ToMm3, sliceVolumePerRow * m3ToMm3, sliceVolumePerLayer * m3ToMm3, countByLength));


            double rowLength = block.getLength() * countByLength + CEMENT_THICKNESS * (countByLength - 1);
            double rowVolume = rowLength * block.getHeight() * CEMENT_THICKNESS;
            double rowVolumePerLayer = rowVolume * (countByWidth - 1);

            log.debug(String.format("row cement mortar one %.0f mm3, layer %.0f mm3. Length %.0f mm3, height %.0f mm3, width %.0f mm3",
                    rowVolume * m3ToMm3, rowVolumePerLayer * m3ToMm3, rowLength * mToMm, block.getHeight() * mToMm, CEMENT_THICKNESS * mToMm));


            double layerLength = block.getLength() * countByLength + CEMENT_THICKNESS * (countByLength - 1);
            double layerWidth = 4.0 * block.getWidth() + CEMENT_THICKNESS * (3.0);
            double layerVolume = layerLength * layerWidth * CEMENT_THICKNESS;

            log.debug(String.format("layer cement mortar %.0f mm3. Length %.0f mm3, width %.0f mm3, height %.0f mm3",
                    layerVolume * m3ToMm3, layerLength * mToMm, layerWidth * mToMm, CEMENT_THICKNESS * mToMm));


            double allSliceVolume = sliceVolumePerLayer * countByHeight;
            double allRowVolume = rowVolumePerLayer * countByHeight;
            double allLayerVolume = layerVolume * (countByHeight - 1);

            double volume = allSliceVolume + allRowVolume + allLayerVolume;

            log.debug(String.format("cement mortar %.3f M3, slice %.0f mm3, row %.0f mm3, layer %.0f mm3 " +
                            "layers %.0f, count by length %.0f, count by width %.0f",
                    volume, allSliceVolume * m3ToMm3, allRowVolume * m3ToMm3, allLayerVolume * m3ToMm3,
                    countByHeight, countByLength, countByWidth));

            return volume;
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
