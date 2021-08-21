package ua.in.sz.house.material;

import org.apache.commons.lang3.NotImplementedException;
import ua.in.sz.house.building.Block;
import ua.in.sz.house.building.House;
import ua.in.sz.house.building.Wall;


public class BrickCalculator implements MaterialCalculator {
    private static final double CEMENT_THICKNESS = 10.0 / 1000.0; // 1 cm

    private final House house;

    public BrickCalculator(House house) {
        this.house = house;
    }

    public static BrickCalculator of(House house) {
        return new BrickCalculator(house);
    }

    @Override
    public Material calculate() {
        Wall wall = house.getWall();
        Block block = wall.getBlock();

        if (Block.CERAMICS_BRICK.equals(block)) {
            double countByLength = Math.ceil(wall.getLength() / (block.getLength() + CEMENT_THICKNESS));
            double countByHeight = Math.ceil(wall.getHeight() / (block.getHeight() + CEMENT_THICKNESS));
            double countByWidth = 4.0;
            return Material.of(Material.Names.BRICK, countByLength * countByHeight * countByWidth);
        } else {
            throw new NotImplementedException("A block calculation not implemented");
        }
    }
}
