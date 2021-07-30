package ua.in.sz.house.building;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

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

    public static House of(Block block) {
        double height = 2.5;
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
