package ua.in.sz.house.material;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Material {
    private final MaterialType materialType;
    private final double quantity;

    public Material(MaterialType materialType, double quantity) {
        this.materialType = materialType;
        this.quantity = quantity;
    }

    public static Material of(MaterialType materialType, double quantity) {
        return new Material(materialType, quantity);
    }
}
