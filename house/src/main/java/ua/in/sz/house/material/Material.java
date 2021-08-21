package ua.in.sz.house.material;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Material {
    private final Names name;
    private final double quantity;
    private double cost;

    public Material(Names name, double quantity, double cost) {
        this.name = name;
        this.quantity = quantity;
        this.cost = cost;
    }

    public static Material of(Names name, double quantity) {
        return new Material(name, quantity, 0.0);
    }

    public enum Names {
        BRICK,
        CEMENT,
        CEMENT_MORTAR,
        SANG
    }
}
