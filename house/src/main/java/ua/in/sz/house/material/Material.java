package ua.in.sz.house.material;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Material {
    private final Name name;
    private final double quantity;
    private double cost;
    private double transportCost;

    public Material(Name name, double quantity, double cost, double transportCost) {
        this.name = name;
        this.quantity = quantity;
        this.cost = cost;
        this.transportCost = transportCost;
    }

    public static Material of(Name name, double quantity) {
        return new Material(name, quantity, 0.0, 0.0);
    }

    public enum Name {
        BRICK,
        CEMENT,
        CEMENT_MORTAR,
        SANG
    }
}
