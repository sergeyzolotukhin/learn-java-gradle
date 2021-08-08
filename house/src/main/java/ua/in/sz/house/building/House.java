package ua.in.sz.house.building;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.boiler.Boiler;

@Slf4j
public class House {
    @Getter
    private final Boiler boiler;
    private final Wall wall;

    @Getter
    private final double height;
    @Getter
    private final double length;
    @Getter
    private final double width;

    House(Boiler boiler, Wall wall, double height, double length, double width) {
        this.boiler = boiler;
        this.wall = wall;
        this.height = height;
        this.length = length;
        this.width = width;
    }

    public double getHeatLoss(double tIn, double tOut) {
        return wall.getHeatLoss(tIn, tOut);
    }

    public double getWallWidth() {
        return wall.getWidth();
    }

    public double blockCount() {
        return wall.blockCount();
    }

    public static HouseBuilder builder() {
        return new HouseBuilder();
    }
}
