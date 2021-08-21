package ua.in.sz.house.material;


import ua.in.sz.house.building.House;

public class CementCalculator implements MaterialCalculator {
    private final House house;

    public CementCalculator(House house) {
        this.house = house;
    }

    public static CementCalculator of(House house) {
        return new CementCalculator(house);
    }

    @Override
    public Material calculate() {
        CementMortarCalculator cementMortarCalculator = CementMortarCalculator.of(house);

        double cementMortar = cementMortarCalculator.calculate().getQuantity();
        double volume = cementMortar / 4.0;
        return Material.of(Material.Names.CEMENT, volume * 1625); // кг в м3 песка
    }
}
