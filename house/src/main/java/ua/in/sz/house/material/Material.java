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
    private double transportCost;

    public Material(Names name, double quantity, double cost, double transportCost) {
        this.name = name;
        this.quantity = quantity;
        this.cost = cost;
        this.transportCost = transportCost;
    }

    public static Material of(Names name, double quantity) {
        return new Material(name, quantity, 0.0, 0.0);
    }

    public enum Names {
        BRICK,
        CEMENT,
        CEMENT_MORTAR,
        SANG
    }
}
