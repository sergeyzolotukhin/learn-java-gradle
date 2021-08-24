package ua.in.sz.house.material;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MaterialPackage implements HasMaterialCode {
    private final MaterialCode materialCode;
    private final double length;
    private final double width;
    private final double height;

    private final double count;
    private final double weight;

    @Override
    public String code() {
        return materialCode.code();
    }
}
