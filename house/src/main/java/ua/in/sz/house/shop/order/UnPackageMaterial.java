package ua.in.sz.house.shop.order;

import ua.in.sz.house.material.MaterialType;
import ua.in.sz.house.material.MaterialUnit;

public record UnPackageMaterial(MaterialType materialType, double quantity, MaterialUnit unit,  double cost)
        implements MaterialOrder.Item {
}

