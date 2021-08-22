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

    public Material(MaterialCode materialCode, double quantity, double cost, double transportCost) {
        this.materialCode = materialCode;
        this.quantity = quantity;
        this.cost = cost;
        this.transportCost = transportCost;
    }

    @Override
    public String code() {
        return materialCode.code();
    }

    public static Material of(MaterialCode materialCode, double quantity) {
        return new Material(materialCode, quantity, 0.0, 0.0);
    }

}
