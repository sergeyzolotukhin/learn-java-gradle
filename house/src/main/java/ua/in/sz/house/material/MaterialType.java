package ua.in.sz.house.material;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MaterialType {
    BRICK("Brick"),
    CEMENT("Cement"),
    CEMENT_MORTAR("Cement mortar"),
    SANG("Sang"),
    WALL("Wall");

    private final String name;
}
