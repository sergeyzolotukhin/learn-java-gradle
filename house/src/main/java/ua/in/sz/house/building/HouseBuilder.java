package ua.in.sz.house.building;

import ua.in.sz.house.boiler.Boiler;

public class HouseBuilder {
    private Block block;
    private Boiler boiler;

    private double height;
    private double length;
    private double width;

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
                .width(wallWidth(block))
                .build();

        return new House(boiler, wall, height, length, width);
    }

    private double wallPerimeter() {
        return 2.0 * width + 2.0 * length;
    }

    private double wallWidth(Block block) {
        if (Block.CERAMICS_BRICK.equals(block)) {
            return 2.0 * block.getLength();
        } else {
            return block.getWidth();
        }
    }
}
