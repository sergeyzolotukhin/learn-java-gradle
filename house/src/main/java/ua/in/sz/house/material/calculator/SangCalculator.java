package ua.in.sz.house.material.calculator;

import ua.in.sz.house.material.Material;
import ua.in.sz.house.material.MaterialType;
import ua.in.sz.house.material.MaterialUnit;

public class SangCalculator {
    public static Material calculate(Material cementMortar) {
        double volume = cementMortar.getQuantity() * 3.0 / 4.0;
        double weight = volume * 1500;
        return new Material(MaterialType.SANG, weight, MaterialUnit.KG, weight); // кг в м3 цемента
    }
}
