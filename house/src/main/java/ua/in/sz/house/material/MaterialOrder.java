package ua.in.sz.house.material;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
public class MaterialOrder implements HasMaterialCode {
    private final MaterialPackage pack;
    private final double packageCount;

    @Override
    public String code() {
        return pack.code();
    }
}
