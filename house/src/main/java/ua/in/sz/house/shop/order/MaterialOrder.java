package ua.in.sz.house.shop.order;

import ua.in.sz.house.material.MaterialType;

public interface MaterialOrder {
    MaterialType getMaterialType();
    double getQuantity();
    double getCost();
}
