package ua.in.sz.house.material;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Material implements HasMaterialCode {
    private final MaterialCode materialCode;
    private final double quantity;

    public Material(MaterialCode materialCode, double quantity) {
        this.materialCode = materialCode;
        this.quantity = quantity;
    }

    @Override
    public String code() {
        return materialCode.code();
    }

    public static Material of(MaterialCode materialCode, double quantity) {
        return new Material(materialCode, quantity);
    }
}
