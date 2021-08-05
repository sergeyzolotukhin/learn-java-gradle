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

    public double getWallWidth() {
        return walls.get(0).getWidth();
    }

    public static House of(Block block) {
        double height = 2.5;

        Wall.Layout wallLayout = wallLayout(block);

        return House.builder()
                .walls(Arrays.asList(
                        Wall.builder().layout(wallLayout).length(10).height(height).build(),
                        Wall.builder().layout(wallLayout).length(10).height(height).build(),
                        Wall.builder().layout(wallLayout).length(10).height(height).build(),
                        Wall.builder().layout(wallLayout).length(10).height(height).build()
                ))
                .build();
    }

    private static Wall.Layout wallLayout(Block block) {
        if (Block.CERAMICS_BRICK.equals(block)) {
            return Wall.Layout.builder().block(block).count(2).byLength(true).build();
        } else {
            return Wall.Layout.builder().block(block).count(1).byLength(false).build();
        }
    }
}
