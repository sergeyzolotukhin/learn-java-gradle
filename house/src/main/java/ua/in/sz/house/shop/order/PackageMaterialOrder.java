package ua.in.sz.house.shop.order;

import ua.in.sz.house.material.MaterialType;
import ua.in.sz.house.shop.MaterialPackage;

public record PackageMaterialOrder(MaterialPackage pack, double quantity, double cost)
        implements MaterialOrder.Item {

    @Override
    public MaterialType materialType() {
        return pack.getMaterialType();
    }
}
