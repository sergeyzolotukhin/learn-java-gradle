package ua.in.sz.house.shop.order;

import ua.in.sz.house.material.MaterialType;

public record UnPackageMaterialOrder(MaterialType materialType, double quantity, double cost)
        implements MaterialOrder.Item {
}

