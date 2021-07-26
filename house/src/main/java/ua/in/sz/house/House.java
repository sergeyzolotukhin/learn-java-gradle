package ua.in.sz.house;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.model.Block;
import ua.in.sz.house.model.Wall;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Builder
public class House {
    private final List<Wall> walls;

    public double getHeatLoss(double tIn, double tOut) {
        return walls.stream()
                .mapToDouble(w -> w.getHeatLoss(tIn, tOut))
                .sum();
    }

    public double getWallSquare() {
        return walls.stream()
                .mapToDouble(Wall::getSquare)
                .sum();
    }

    public static void main(String[] args) {
        Block block = Block.GAS_CONCRETE_BLOCK_D300;
        int height = 3;

        House house = House.builder()
                .walls(Arrays.asList(
                        Wall.builder().block(block).length(10).height(height).build(),
                        Wall.builder().block(block).length(10).height(height).build(),
                        Wall.builder().block(block).length(10).height(height).build(),
                        Wall.builder().block(block).length(10).height(height).build()
                ))
                .build();

        double heatLossKWt = house.getHeatLoss(20.0, -20.0) / 1000.0;
        log.info("Heat loss is {} KWt on wall square {} M2", String.format("%.2f", heatLossKWt),
                house.getWallSquare());
    }
}
