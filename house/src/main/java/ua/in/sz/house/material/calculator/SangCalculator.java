package ua.in.sz.house.material.calculator;

import ua.in.sz.house.material.Material;
import ua.in.sz.house.material.MaterialCode;

public class SangCalculator {
    public static Material calculate(Material cementMortar) {
        double volume = cementMortar.getQuantity() * 3.0 / 4.0;
        return Material.of(MaterialCode.SANG, volume * 1500); // кг в м3 цемента
    }
}