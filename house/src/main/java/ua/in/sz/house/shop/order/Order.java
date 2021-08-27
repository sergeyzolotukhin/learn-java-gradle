package ua.in.sz.house.shop.order;

import ua.in.sz.house.material.MaterialType;

public interface Order {
    MaterialType materialType();
    double quantity();
    double cost();
}
