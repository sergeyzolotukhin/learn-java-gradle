package ua.in.sz.house.house;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.boiler.Boiler;
import ua.in.sz.house.transport.Place;

@Slf4j
@Getter
public class House {
    private final Place place;
    private final Boiler boiler;
    private final Wall wall;
    private final double height;
    private final double length;
    private final double width;

    House(Place place, Boiler boiler, Wall wall, double height, double length, double width) {
        this.place = place;
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

    public static HouseBuilder builder() {
        return new HouseBuilder();
    }
}
