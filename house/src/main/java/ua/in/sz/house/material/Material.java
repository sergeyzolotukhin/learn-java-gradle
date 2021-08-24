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

    // TODO: remove
//    private double cost;
//    private double transportCost;
//    private boolean required;

    public Material(MaterialCode materialCode, double quantity) {
        this.materialCode = materialCode;
        this.quantity = quantity;
//        this.cost = cost;
//        this.transportCost = transportCost;
//        this.required = required;
    }

    @Override
    public String code() {
        return materialCode.code();
    }

    public static Material of(MaterialCode materialCode, double quantity) {
        return new Material(materialCode, quantity);
    }

}
