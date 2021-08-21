package ua.in.sz.house.material.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;
import ua.in.sz.house.building.Block;
import ua.in.sz.house.building.House;
import ua.in.sz.house.building.Wall;
import ua.in.sz.house.material.Material;
import ua.in.sz.house.material.MaterialCalculator;

@Slf4j
public class CementMortarCalculator implements MaterialCalculator {
    private static final double CEMENT_THICKNESS = 10.0 / 1000.0; // 1 cm

    private final House house;

    public CementMortarCalculator(House house) {
        this.house = house;
    }

    public static CementMortarCalculator of(House house) {
        return new CementMortarCalculator(house);
    }

    /**
     * Объем цементно песчаного раствора М3
     */
    @Override
    public Material calculate() {
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

            return Material.of(Material.Names.CEMENT_MORTAR, volume);
        } else {
            throw new NotImplementedException("A block calculation not implemented");
        }
    }
}
