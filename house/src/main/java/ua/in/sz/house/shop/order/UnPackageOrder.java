package ua.in.sz.house.shop.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ua.in.sz.house.material.MaterialType;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class UnPackageOrder implements Order {
    private final MaterialType materialType;
    private final double quantity;
    private final double cost;
}

