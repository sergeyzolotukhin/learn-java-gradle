package ua.in.sz.house.material.calculator;

import org.apache.commons.lang3.NotImplementedException;
import ua.in.sz.house.house.Block;
import ua.in.sz.house.house.House;
import ua.in.sz.house.house.Wall;
import ua.in.sz.house.material.Material;
import ua.in.sz.house.material.MaterialCode;


public class BrickCalculator {
    private static final double CEMENT_THICKNESS = 10.0 / 1000.0; // 1 cm

    public static Material calculate(House house) {
        Wall wall = house.getWall();
        Block block = wall.getBlock();

        if (Block.CERAMICS_BRICK.equals(block)) {
            double countByLength = Math.ceil(wall.getLength() / (block.getLength() + CEMENT_THICKNESS));
            double countByHeight = Math.ceil(wall.getHeight() / (block.getHeight() + CEMENT_THICKNESS));
            double countByWidth = 4.0;
            return Material.of(MaterialCode.BRICK, countByLength * countByHeight * countByWidth);
        } else {
            throw new NotImplementedException("A block calculation not implemented");
        }
    }
}
