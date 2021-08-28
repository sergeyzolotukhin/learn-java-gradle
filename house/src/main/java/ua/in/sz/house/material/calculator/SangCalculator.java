package ua.in.sz.house.material.calculator;

import ua.in.sz.house.material.Material;
import ua.in.sz.house.material.MaterialType;
import ua.in.sz.house.material.MaterialUnit;

public class SangCalculator {
    public static Material calculate(Material cementMortar) {
        double volume = cementMortar.getQuantity() * 3.0 / 4.0;
        return new Material(MaterialType.SANG, volume * 1500, MaterialUnit.KG); // кг в м3 цемента
    }
}
