package ua.in.sz.house.house;

import ua.in.sz.house.boiler.Boiler;
import ua.in.sz.house.transport.Place;

public class HouseBuilder {
    private Place place;
    private Block block;
    private Boiler boiler;

    private double height;
    private double length;
    private double width;

    public HouseBuilder place(Place place) {
        this.place = place;
        return this;
    }

    public HouseBuilder boiler(Boiler boiler) {
        this.boiler = boiler;
        return this;
    }

    public HouseBuilder block(Block block) {
        this.block = block;
        return this;
    }

    public HouseBuilder size(double width, double length, double height) {
        this.width = width;
        this.length = length;
        this.height = height;
        return this;
    }

    public House build() {
        Wall wall = Wall.builder()
                .block(block)
                .height(height)
                .length(wallPerimeter())
                .build();

        return new House(place, boiler, wall, height, length, width);
    }

    private double wallPerimeter() {
        return 2.0 * width + 2.0 * length;
    }
}
