package ua.in.sz.house;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.model.Wall;

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


}
