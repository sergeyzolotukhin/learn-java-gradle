package ua.in.sz.house;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.model.Block;
import ua.in.sz.house.model.House;
import ua.in.sz.house.model.Wall;

import java.util.Arrays;

@Slf4j
public class Main {
    public static void main(String[] args) {
        House house = buildHouse();

        double heatLossKWt = house.getHeatLoss(20.0, -20.0) / 1000.0;
        log.info("Heat loss is {} KWt on wall square {} M2", String.format("%.2f", heatLossKWt),
                house.getWallSquare());
    }

    private static House buildHouse() {
        Block block = Block.CERAMICS_BRICK;
        int height = 3;

        return House.builder()
                .walls(Arrays.asList(
                        Wall.builder().block(block).length(10).height(height).build(),
                        Wall.builder().block(block).length(10).height(height).build(),
                        Wall.builder().block(block).length(10).height(height).build(),
                        Wall.builder().block(block).length(10).height(height).build()
                ))
                .build();
    }
}
