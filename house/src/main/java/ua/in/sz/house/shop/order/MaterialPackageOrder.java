package ua.in.sz.house.shop.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ua.in.sz.house.shop.MaterialPackage;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class MaterialPackageOrder implements MaterialOrder {
    private final MaterialPackage pack;
    private final double quantity;
    private final double cost;

    @Override
    public String code() {
        return pack.code();
    }
}
