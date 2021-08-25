package ua.in.sz.house.shop;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ua.in.sz.house.material.MaterialType;

@Getter
@RequiredArgsConstructor
public class MaterialPackage {
    private final MaterialType materialType;
    private final double length;
    private final double width;
    private final double height;

    private final double count;
    private final double weight;
}
