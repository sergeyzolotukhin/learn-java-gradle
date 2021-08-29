package ua.in.sz.house.shop.order;

import ua.in.sz.house.material.MaterialType;
import ua.in.sz.house.material.MaterialUnit;
import ua.in.sz.house.shop.MaterialPackage;

public record PackageMaterial(MaterialPackage pack, double quantity, MaterialUnit unit, double cost)
        implements MaterialOrder.Item {

    @Override
    public MaterialType materialType() {
        return pack.getMaterialType();
    }

    @Override
    public MaterialOrder.Item add(MaterialOrder.Item item) {
        PackageMaterial added = (PackageMaterial) item;
        return new PackageMaterial(pack, quantity + added.quantity(), unit, cost + added.cost());
    }
}
