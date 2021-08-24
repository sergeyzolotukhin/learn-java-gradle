package ua.in.sz.house.shop;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
public class MaterialPackageOrder implements MaterialOrder {
    private final MaterialPackage pack;
    private final double quantity;
    private final double cost;

    @Override
    public String code() {
        return pack.code();
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
