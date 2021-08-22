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
    private double cost;
    private double transportCost;
    private boolean required = true;

    public Material(MaterialCode materialCode, double quantity, double cost, double transportCost, boolean required) {
        this.materialCode = materialCode;
        this.quantity = quantity;
        this.cost = cost;
        this.transportCost = transportCost;
        this.required = required;
    }

    @Override
    public String code() {
        return materialCode.code();
    }

    public static Material of(MaterialCode materialCode, double quantity) {
        return of(materialCode, quantity, true);
    }

    public static Material of(MaterialCode materialCode, double quantity, boolean required) {
        return new Material(materialCode, quantity, 0.0, 0.0, required);
    }

}
