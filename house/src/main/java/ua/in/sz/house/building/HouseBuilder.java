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
        Wall.Layout wallLayout = wallLayout(block);
        double perimeter = 2.0 * width + 2.0 * length;

        Wall wall = Wall.builder()
                .layout(wallLayout)
                .length(perimeter)
                .height(height)
                .build();

        return new House(boiler, wall, height, length, width);
    }

    private static Wall.Layout wallLayout(Block block) {
        if (Block.CERAMICS_BRICK.equals(block)) {
            return Wall.Layout.builder().block(block).count(2).byLength(true).build();
        } else {
            return Wall.Layout.builder().block(block).count(1).byLength(false).build();
        }
    }
}
