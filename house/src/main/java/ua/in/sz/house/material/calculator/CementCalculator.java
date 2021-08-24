package ua.in.sz.house.material.calculator;


import ua.in.sz.house.material.Material;
import ua.in.sz.house.material.MaterialCode;

public class CementCalculator {
    public static Material calculate(Material cementMortar) {
        double volume = cementMortar.getQuantity() / 4.0;
        return Material.of(MaterialCode.CEMENT, volume * 1625); // кг в м3 песка
    }
}
