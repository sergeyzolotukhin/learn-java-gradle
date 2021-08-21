package ua.in.sz.house.material;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import ua.in.sz.house.building.Block;
import ua.in.sz.house.building.House;
import ua.in.sz.house.building.Wall;


@AllArgsConstructor(staticName = "of")
public class BrickCalculator {
    private static final double CEMENT_THICKNESS = 10.0 / 1000.0; // 1 cm

    private final House house;

    public double blockCount() {
        Wall wall = house.getWall();
        Block block = wall.getBlock();

        if (Block.CERAMICS_BRICK.equals(block)) {
            double countByLength = Math.ceil(wall.getLength() / (block.getLength() + CEMENT_THICKNESS));
            double countByHeight = Math.ceil(wall.getHeight() / (block.getHeight() + CEMENT_THICKNESS));
            double countByWidth = 4.0;
            return countByLength * countByHeight * countByWidth;
        } else {
            throw new NotImplementedException("A block calculation not implemented");
        }
    }
}
