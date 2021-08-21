package ua.in.sz.house.material;

import ua.in.sz.house.building.House;

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
        return Material.of(Material.Names.SANG, volume * 1500); // кг в м3 цемента
    }
}
