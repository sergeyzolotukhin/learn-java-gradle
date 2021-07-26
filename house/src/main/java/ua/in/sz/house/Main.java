package ua.in.sz.house;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.model.Block;
import ua.in.sz.house.model.House;
import ua.in.sz.house.model.Wall;

import java.util.Arrays;

@Slf4j
public class Main {
    public static void main(String[] args) {
        House ceramicsHouse = buildHouse(Block.CERAMICS_BRICK);

        log.info("Heat loss is {} KWt on wall square {} M2",
                formatHeatLossKW(ceramicsHouse.getHeatLoss(20.0, -20.0)),
                ceramicsHouse.getWallSquare());

        House gasConcreteHouse = buildHouse(Block.GAS_CONCRETE_BLOCK_D300);

        log.info("Heat loss is {} KWt on wall square {} M2",
                formatHeatLossKW(gasConcreteHouse.getHeatLoss(20.0, -20.0)),
                gasConcreteHouse.getWallSquare());
    }

    public static House buildHouse(Block block) {
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

    private static String formatHeatLossKW(double heatLoss) {
        return String.format("%.2f", heatLoss / 1000.0);
    }
}
