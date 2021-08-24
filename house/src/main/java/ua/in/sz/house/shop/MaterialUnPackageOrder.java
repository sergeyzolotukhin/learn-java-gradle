package ua.in.sz.house.shop;

import lombok.RequiredArgsConstructor;
import ua.in.sz.house.material.MaterialCode;

@RequiredArgsConstructor(staticName = "of")
public class MaterialUnPackageOrder implements MaterialOrder {
    private final MaterialCode materialCode;
    private final double quantity;
    private final double cost;

    @Override
    public String code() {
        return materialCode.code();
    }

    @Override
    public double getQuantity() {
        return quantity;
    }

    @Override
    public double getCost() {
        return cost;
    }
}

