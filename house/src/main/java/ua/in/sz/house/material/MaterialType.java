package ua.in.sz.house.material;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MaterialType {
    // building parts
    HOUSE("House"),
    FOUNDATION("Foundation"),
    WALL("Wall"),

    // intermediate material
    CEMENT_MORTAR("Cement mortar"),
    FORMWORK("Formwork"), // опалубка

    // raw materials
    BRICK("Brick"),
    CEMENT("Cement"),
    SANG("Sang"),
    GRAVEL("Gravel"),
    WOOD("Wood"),
    ;

    private final String name;
}
