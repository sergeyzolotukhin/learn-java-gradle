package ua.in.sz.house.material.impl;

import ua.in.sz.house.building.House;
import ua.in.sz.house.material.Material;
import ua.in.sz.house.material.MaterialCalculator;
import ua.in.sz.house.material.MaterialCode;

public class SangCalculator implements MaterialCalculator {
    private final House house;

    public SangCalculator(House house) {
        this.house = house;
    }

    public static SangCalculator of(House house) {
        return new SangCalculator(house);
    }

    @Override
    public Material calculate() {
        CementMortarCalculator cementMortarCalculator = CementMortarCalculator.of(house);

        double cementMortar = cementMortarCalculator.calculate().getQuantity();
        double volume = cementMortar * 3.0 / 4.0;
        return Material.of(MaterialCode.SANG, volume * 1500); // кг в м3 цемента
    }
}
