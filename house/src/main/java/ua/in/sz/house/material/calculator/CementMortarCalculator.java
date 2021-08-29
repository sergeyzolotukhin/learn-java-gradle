package ua.in.sz.house.material.calculator;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;
import ua.in.sz.house.house.Block;
import ua.in.sz.house.house.House;
import ua.in.sz.house.house.Wall;
import ua.in.sz.house.material.Material;
import ua.in.sz.house.material.MaterialType;
import ua.in.sz.house.material.MaterialUnit;

@Slf4j
public class CementMortarCalculator {
    private static final double CEMENT_THICKNESS = 10.0 / 1000.0; // 1 cm

    /**
     * Объем цементно песчаного раствора М3
     */
    public static Material calculate(House house) {
        Wall wall = house.getWall();
        Block block = wall.getBlock();

        if (Block.CERAMICS_BRICK.equals(block)) {
            int m3ToMm3 = 1_000_000_000;
            int mToMm = 1_000;

            double countByHeight = Math.ceil(wall.getHeight() / (block.getHeight() + CEMENT_THICKNESS));
            double countByLength = Math.ceil(wall.getLength() / (block.getLength() + CEMENT_THICKNESS));
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

            return new Material(MaterialType.CEMENT_MORTAR, volume, MaterialUnit.M3, 0.0);
        } else {
            throw new NotImplementedException("A block calculation not implemented");
        }
    }
}
