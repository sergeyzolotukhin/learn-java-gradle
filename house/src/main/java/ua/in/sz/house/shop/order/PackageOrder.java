package ua.in.sz.house.shop.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ua.in.sz.house.material.MaterialType;
import ua.in.sz.house.shop.MaterialPackage;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class PackageOrder implements Order {
    private final MaterialPackage pack;
    private final double quantity;
    private final double cost;

    @Override
    public MaterialType getMaterialType() {
        return pack.getMaterialType();
    }
}
