package ua.in.sz.house.shop.order;

import ua.in.sz.house.material.HasMaterialCode;

public interface MaterialOrder extends HasMaterialCode {
    double getQuantity();
    double getCost();
}
