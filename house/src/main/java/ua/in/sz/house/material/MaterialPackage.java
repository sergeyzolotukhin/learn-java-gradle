package ua.in.sz.house.material;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MaterialPackage implements HasMaterialCode {
    private final MaterialCode materialCode;
    private final double length;
    private final double width;
    private final double count;
    private final double weight;

    @Override
    public String code() {
        return materialCode.code();
    }
}
