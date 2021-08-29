package ua.in.sz.house.material.calculator;


import ua.in.sz.house.material.Material;
import ua.in.sz.house.material.MaterialType;
import ua.in.sz.house.material.MaterialUnit;

public class CementCalculator {
    public static Material calculate(Material cementMortar) {
        double volume = cementMortar.getQuantity() / 4.0;
        return new Material(MaterialType.CEMENT, volume * 1625, MaterialUnit.KG, volume * 1625); // кг в м3 песка
    }
}
